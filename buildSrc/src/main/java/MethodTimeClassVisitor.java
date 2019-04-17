import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;


/***
 * Created by duke on 2019/4/9.
 */
public class MethodTimeClassVisitor extends ClassVisitor {

    public MethodTimeClassVisitor(ClassVisitor classVisitor) {
        super(Opcodes.ASM5, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        mv = new AdviceAdapter(Opcodes.ASM5, mv, access, name, desc) {

            private boolean inject = false;

            @Override
            public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
                //Lcom/kalengo/commonlib/MethodTime;就是下面这个代码来的，因采用buildSrc，所以引用不到
                //Type.getDescriptor(MethodTime.class)
                System.out.println("Lcom/kalengo/commonlib/MethodTime;".equals(desc));
                if ("Lcom/kalengo/commonlib/MethodTime;".equals(desc)) {
                    inject = true;
                }
                return super.visitAnnotation(desc, visible);
            }

            @Override
            protected void onMethodEnter() {
                if (inject) {
                    Label l0 = new Label();
                    mv.visitLabel(l0);
                    mv.visitLineNumber(11, l0);
                    mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                    mv.visitLdcInsn("========start=========");
                    mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
                    Label l1 = new Label();
                    mv.visitLabel(l1);
                    mv.visitLineNumber(12, l1);
                    mv.visitLdcInsn(name);
                    mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J", false);
                    mv.visitMethodInsn(INVOKESTATIC, "com/kalengo/commonlib/TimeCache", "setStartTime", "(Ljava/lang/String;J)V", false);
                }
            }

            @Override
            protected void onMethodExit(int opcode) {
                if (inject) {
                    Label l0 = new Label();
                    mv.visitLabel(l0);
                    mv.visitLineNumber(15, l0);
                    mv.visitLdcInsn(name);
                    mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J", false);
                    mv.visitMethodInsn(INVOKESTATIC, "com/kalengo/commonlib/TimeCache", "setEndTime", "(Ljava/lang/String;J)V", false);
                    Label l1 = new Label();
                    mv.visitLabel(l1);
                    mv.visitLineNumber(16, l1);
                    mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                    mv.visitLdcInsn(name);
                    mv.visitMethodInsn(INVOKESTATIC, "com/kalengo/commonlib/TimeCache", "getCostTime", "(Ljava/lang/String;)Ljava/lang/String;", false);
                    mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
                    Label l2 = new Label();
                    mv.visitLabel(l2);
                    mv.visitLineNumber(17, l2);
                    mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                    mv.visitLdcInsn("========end=========");
                    mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
                }
            }
        };
        return mv;
    }
}