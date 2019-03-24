|Branch|Status|
|------|------|
|[master](https://github.com/softeng-teamn/Prototype-Project/tree/master)|[![Build Status](https://travis-ci.com/softeng-teamn/Prototype-Project.svg?branch=master)](https://travis-ci.com/softeng-teamn/Prototype-Project)|
|[dev](https://github.com/softeng-teamn/Prototype-Project/tree/dev)|[![Build Status](https://travis-ci.com/softeng-teamn/Prototype-Project.svg?branch=dev)](https://travis-ci.com/softeng-teamn/Prototype-Project)|
|[db](https://github.com/softeng-teamn/Prototype-Project/tree/db)|[![Build Status](https://travis-ci.com/softeng-teamn/Prototype-Project.svg?branch=db)](https://travis-ci.com/softeng-teamn/Prototype-Project)|
|[ui](https://github.com/softeng-teamn/Prototype-Project/tree/ui)|[![Build Status](https://travis-ci.com/softeng-teamn/Prototype-Project.svg?branch=ui)](https://travis-ci.com/softeng-teamn/Prototype-Project)|

To assemble a jar file for your project, run the "jar" gradle task, either through IntelliJ or by doing
`gradle jar` on a terminal. Gradle will automatically download all dependencies needed to compile your jar file,
which will be stored in the build/libs folder.

Make sure to edit the main class attribute the build.gradle file, you'll need to change it in order to obtain
a working jar file.
