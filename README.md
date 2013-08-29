scalassist-example
==================

##Under Construction## Scalassist is a tool to make Java bytecode-engineering libraries more usable with Scala. It updates a pickled Scala signature to reflect changes made to the bytecode.


Not every Scala construct has an equivalent in Java, some information is stored in the Scala signature annotation. For most things is suffices to alter the bytecode alone, but in other cases is is necessary to alter the Scala signature correspondingly.
