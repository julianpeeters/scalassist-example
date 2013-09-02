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

/*
//Scala method reflection: Doesn't seem to work with Javassist injected methods, but Java reflection works
  val m = runtimeMirror(getClass.getClassLoader)
  val instanceMirror = m.reflect(new HelloWorld)

  val methodX = typeOf[HelloWorld].declaration(newTermName("sayHello")).asMethod
  val mm = instanceMirror.reflectMethod(methodX) 
    mm("Julian")
*/


val dynamicField = classOf[HelloWorld].getDeclaredField("dynamicField")
println(dynamicField.get(instance))



}

class Inject(var targetClass: String, var targetMethod: String)  {

  def  injectAround() =  {
    val cp = ClassPool.getDefault()
    cp.appendClassPath(new javassist.LoaderClassPath(getClass().getClassLoader()))
    val ctclass = cp.get(targetClass)

/*
Javassist method Injection:
    val method1 = ctclass.getDeclaredMethod(targetMethod)
    method1.insertBefore("""System.out.println("Code injected before method");""")
    method1.insertAfter("""System.out.println("Code injected after method");""")
*/

    val field = CtField.make(
    //"private static final long serialVersionUID = 1L;", 
    """String dynamicField = "Hello Javassist";""", 
    ctclass)
    ctclass.addField(field)

    val clazz = ctclass.toClass()

    clazz
  }
}
