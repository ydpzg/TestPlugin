import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

/***
 * Created by duke on 2019/4/1.
 */
class CustomPlugin implements Plugin<Project> {

    void apply(Project project) {
        println 'hello CustomPlugin'

        //./gradlew -q hello
        //测试build.gradle的参数获取
        def extension = project.extensions.create('greeting', GreetingPluginExtension)
        project.task('hello') {
            doLast {
                println extension.message
                println extension.greeter
            }
        }

        //自定义transform进行处理
        def android = project.extensions.getByType(AppExtension)
        android.registerTransform(new CustomTransform(project))
    }
}

class GreetingPluginExtension {
    String message = "GreetingPluginExtension default message"
    String greeter = "GreetingPluginExtension default greeter"
}
