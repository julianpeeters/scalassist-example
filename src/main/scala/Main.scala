//Adapted for scala from Sandeep Awasthi's example: http://thoughtpage.wordpress.com/2012/07/29/inject-code-using-javaassist-simple-example/
import javassist._
import scala.reflect.runtime.{universe => ru}
import ru._


class MyClass(x: String)


class HelloWorld {
    def sayHello(name: String) = {
        println("Hello "+ name);
    }
}

object Test extends App{

  val inject1 = new Inject("HelloWorld","sayHello");
  val clazz = inject1.injectAround();
  val m = ru.runtimeMirror(getClass.getClassLoader)
  val instanceMirror = m.reflect(new HelloWorld)
  val methodX = ru.typeOf[HelloWorld].declaration(newTermName("sayHello")).asMethod
  val mm = instanceMirror.reflectMethod(methodX) 

  mm("Julian")
}

class Inject(var targetClass: String, var targetMethod: String)  {

  def Inject1(targetClass: String, targetMethod: String) = {
    this.targetClass = targetClass;
    this.targetMethod = targetMethod;
  }

  def  injectAround() =  {
    val cp = ClassPool.getDefault()
    cp.appendClassPath(new javassist.LoaderClassPath(getClass().getClassLoader()))
    val ctClazz = cp.get(targetClass)
    val method1 = ctClazz.getDeclaredMethod(targetMethod)
    method1.insertBefore("""System.out.println("Code injected before method");""")
    method1.insertAfter("""System.out.println("Code injected after method");""")
    val clazz = ctClazz.toClass()

    clazz
  }
}
