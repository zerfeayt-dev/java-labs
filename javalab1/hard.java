import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.nio.file.Files;

public class CustomLoaderApp {

    // Власний ClassLoader, який завантажує байт-код напряму з файлу .class
    static class HotSwapClassLoader extends ClassLoader {
        private final File classFile;

        public HotSwapClassLoader(File classFile) {
            // Передаємо null як parent, щоб обійти стандартний механізм кешування класів
            super(null);
            this.classFile = classFile;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            if ("TestModule".equals(name)) {
                try {
                    byte[] bytes = Files.readAllBytes(classFile.toPath());
                    return defineClass(name, bytes, 0, bytes.length);
                } catch (Exception e) {
                    throw new ClassNotFoundException("Не вдалося завантажити клас", e);
                }
            }
            return ClassLoader.getSystemClassLoader().loadClass(name);
        }
    }

    public static void main(String[] args) throws Exception {
        File javaFile = new File("src/TestModule.java");
        File classFile = new File("src/TestModule.class");
        long lastModified = 0;

        System.out.println("Моніторинг запущено. Змініть текст у src/TestModule.java і збережіть файл (Ctrl+S)...");

        while (true) {
            if (javaFile.exists() && javaFile.lastModified() > lastModified) {
                lastModified = javaFile.lastModified();
                
                JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
                int compilationResult = compiler.run(null, null, null, javaFile.getPath());

                if (compilationResult == 0 && classFile.exists()) {
                    HotSwapClassLoader loader = new HotSwapClassLoader(classFile);
                    Class<?> reloadedClass = loader.loadClass("TestModule");
                    Object instance = reloadedClass.getDeclaredConstructor().newInstance();
                    System.out.println("Вивід об'єкта: " + instance);
                } else {
                    System.out.println("Помилка компіляції!");
                }
            }
            Thread.sleep(1000);
        }
    }
}
