package cn.sast.framework.rewrite;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import soot.Body;
import soot.BooleanType;
import soot.ByteType;
import soot.CharType;
import soot.DoubleType;
import soot.FloatType;
import soot.IntType;
import soot.Local;
import soot.LongType;
import soot.RefType;
import soot.ShortType;
import soot.SootClass;
import soot.SootMethod;
import soot.SootResolver;
import soot.Type;
import soot.Unit;
import soot.Value;
import soot.jimple.Jimple;
import soot.jimple.StringConstant;

/* loaded from: StringConcatRewriter.class */
public class StringConcatRewriter {
    private static final String JAVA_OBJECT = "java.lang.Object";
    private static final String JAVA_STRING = "java.lang.String";
    private static final String JAVA_STRINGBUILDER = "java.lang.StringBuilder";
    private static final String JAVA_STRINGBUILDER_INIT = "void <init>()";
    private static final String JAVA_STRINGBUILDER_TOSTRING = "java.lang.String toString()";
    private static final String APPEND = "append";
    private SootMethod StringBuilder_init;
    private SootMethod StringBuilder_toString;
    private SootMethod defaultStringBuilder_append;
    private Map<Type, SootMethod> StringBuilder_append;

    public StringConcatRewriter() {
        init();
    }

    private void init() {
        SootClass java_lang_StringBuilder = SootResolver.v().resolveClass(JAVA_STRINGBUILDER, 2);
        this.StringBuilder_init = java_lang_StringBuilder.getMethod(JAVA_STRINGBUILDER_INIT);
        this.StringBuilder_toString = java_lang_StringBuilder.getMethod(JAVA_STRINGBUILDER_TOSTRING);
        this.StringBuilder_append = new HashMap();
        this.defaultStringBuilder_append = java_lang_StringBuilder.getMethod(APPEND, Collections.singletonList(RefType.v(JAVA_OBJECT)), RefType.v(JAVA_STRINGBUILDER));
        this.StringBuilder_append.put(BooleanType.v(), java_lang_StringBuilder.getMethod(APPEND, Collections.singletonList(BooleanType.v()), RefType.v(JAVA_STRINGBUILDER)));
        this.StringBuilder_append.put(CharType.v(), java_lang_StringBuilder.getMethod(APPEND, Collections.singletonList(CharType.v()), RefType.v(JAVA_STRINGBUILDER)));
        this.StringBuilder_append.put(ByteType.v(), java_lang_StringBuilder.getMethod(APPEND, Collections.singletonList(IntType.v()), RefType.v(JAVA_STRINGBUILDER)));
        this.StringBuilder_append.put(ShortType.v(), java_lang_StringBuilder.getMethod(APPEND, Collections.singletonList(IntType.v()), RefType.v(JAVA_STRINGBUILDER)));
        this.StringBuilder_append.put(IntType.v(), java_lang_StringBuilder.getMethod(APPEND, Collections.singletonList(IntType.v()), RefType.v(JAVA_STRINGBUILDER)));
        this.StringBuilder_append.put(LongType.v(), java_lang_StringBuilder.getMethod(APPEND, Collections.singletonList(LongType.v()), RefType.v(JAVA_STRINGBUILDER)));
        this.StringBuilder_append.put(FloatType.v(), java_lang_StringBuilder.getMethod(APPEND, Collections.singletonList(FloatType.v()), RefType.v(JAVA_STRINGBUILDER)));
        this.StringBuilder_append.put(DoubleType.v(), java_lang_StringBuilder.getMethod(APPEND, Collections.singletonList(DoubleType.v()), RefType.v(JAVA_STRINGBUILDER)));
        this.StringBuilder_append.put(RefType.v(JAVA_STRING), java_lang_StringBuilder.getMethod(APPEND, Collections.singletonList(RefType.v(JAVA_STRING)), RefType.v(JAVA_STRINGBUILDER)));
    }

    public LinkedList<Unit> rewriteMakeConcat(Body body, Value outValue, List<Value> concatArgs) {
        return rewrite(body, outValue, concatArgs);
    }

    public LinkedList<Unit> rewriteMakeConcatWithConstants(Body body, Value outValue, List<Value> concatArgs, List<Value> bootstrapArgs) {
        StringConstant stringConstant = (Value) bootstrapArgs.get(0);
        if (!(stringConstant instanceof StringConstant)) {
            throw new IllegalArgumentException("makeConcatWithConstants argument 'recipe' must be a String!");
        }
        String recipe = stringConstant.value;
        List<Value> args = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        int argIndex = 0;
        int constIndex = 1;
        int length = recipe.length();
        for (int i = 0; i < length; i++) {
            char c = recipe.charAt(i);
            if (c == 1) {
                if (sb.length() > 0) {
                    args.add(StringConstant.v(sb.toString()));
                    sb.setLength(0);
                }
                int i2 = argIndex;
                argIndex++;
                args.add(concatArgs.get(i2));
            } else if (c == 2) {
                int i3 = constIndex;
                constIndex++;
                StringConstant constant = bootstrapArgs.get(i3);
                sb.append(constant.value);
            } else {
                sb.append(c);
            }
        }
        if (sb.length() > 0) {
            args.add(StringConstant.v(sb.toString()));
        }
        return rewrite(body, outValue, args);
    }

    private LinkedList<Unit> rewrite(Body body, Value outValue, List<Value> args) {
        LinkedList<Unit> newUnits = new LinkedList<>();
        Local stringBuilderLocal = createNewLocal(body, RefType.v(JAVA_STRINGBUILDER));
        newUnits.add(Jimple.v().newAssignStmt(stringBuilderLocal, Jimple.v().newNewExpr(RefType.v(JAVA_STRINGBUILDER))));
        newUnits.add(Jimple.v().newInvokeStmt(Jimple.v().newSpecialInvokeExpr(stringBuilderLocal, this.StringBuilder_init.makeRef())));
        for (Value value : args) {
            if (value instanceof StringConstant) {
                Value valueCreateNewLocal = createNewLocal(body, RefType.v(JAVA_STRING));
                newUnits.add(Jimple.v().newAssignStmt(valueCreateNewLocal, value));
                value = valueCreateNewLocal;
            }
            newUnits.add(Jimple.v().newInvokeStmt(Jimple.v().newVirtualInvokeExpr(stringBuilderLocal, this.StringBuilder_append.getOrDefault(value.getType(), this.defaultStringBuilder_append).makeRef(), value)));
        }
        newUnits.add(Jimple.v().newAssignStmt(outValue, Jimple.v().newVirtualInvokeExpr(stringBuilderLocal, this.StringBuilder_toString.makeRef())));
        return newUnits;
    }

    private Local createNewLocal(Body body, Type t) {
        Local local = Jimple.v().newLocal("$v" + body.getLocals().size(), t);
        body.getLocals().add(local);
        return local;
    }
}
