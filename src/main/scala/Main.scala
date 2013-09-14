//Adapted for scala from Sandeep Awasthi's example: http://thoughtpage.wordpress.com/2012/07/29/inject-code-using-javaassist-simple-example/
import javassist._
import scala.reflect.runtime.universe._

class HelloWorld {
    val x = "hello world"
    def sayHello(name: String) = {
        println("Hello "+ name);
    }
}

object Test extends App{
  val inject1 = new Inject("HelloWorld","sayHello");
  val clazz = inject1.injectAround();
  val instance = clazz.newInstance()

  val dynamicField = instance.getClass.getDeclaredField("serialVersionUID")
  dynamicField.setAccessible(true)
    println(dynamicField.get())

}

class Inject(var targetClass: String, var targetMethod: String)  {

  def  injectAround() =  {
    val cp = ClassPool.getDefault()
    cp.appendClassPath(new javassist.LoaderClassPath(getClass().getClassLoader()))
    val ctclass = cp.get(targetClass)

    val field = CtField.make(
    "private static final long serialVersionUID = 1L;", 
   // """private long serialVersionUID = 1L;""", 
   // """String dynamicField = "Hello Javassist";""", 
    ctclass)
    ctclass.addField(field)

    val clazz = ctclass.toClass()

    clazz
  }
}
