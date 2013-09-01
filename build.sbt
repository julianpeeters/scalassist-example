name := "scalassist-example"

version := "0.1"

organization := "com.julianpeeters"

scalaVersion := "2.10.2"
//scalaVersion := "2.10.1"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

resolvers += "Sonatype OSS REls" at "https://oss.sonatype.org/content/repositories/releases"

libraryDependencies ++= Seq( 
 // "org.scalatest" %% "scalatest" % "1.8" % "test",
"org.javassist" % "javassist" % "3.14.0-GA",
  "org.apache.avro" % "avro" % "1.7.4",
   "com.novus" %% "salat" % "1.9.2", 
 //  "com.novus" %% "salat" % "1.9.2",
   "org.slf4j" % "slf4j-simple" % "1.6.4"
)

            
