Steps to reproduce:

* Install Eclipse Java 2019-03 <https://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/2019-03/R/eclipse-java-2019-03-R-win32-x86_64.zip>
* Clone this repository into the eclipse workspace
* In Eclipse select `Import > General > Existing Projects into Workspace` and import this project
* If `Build Automatically` is enabled you should see an error Dialog pop up with the following error:

Plug-in: `org.eclipse.jdt.core`
Message: `Errors running builder 'Java Builder' on project 'eclipse-bug-annotation-processing-NullPointerException'.`

Stacktrace:

```
java.lang.NullPointerException
	at org.eclipse.jdt.internal.compiler.lookup.PackageBinding.getPackage0(PackageBinding.java:176)
	at org.eclipse.jdt.internal.compiler.lookup.LookupEnvironment.createPackage(LookupEnvironment.java:1100)
	at org.eclipse.jdt.internal.compiler.lookup.CompilationUnitScope.buildTypeBindings(CompilationUnitScope.java:132)
	at org.eclipse.jdt.internal.compiler.lookup.LookupEnvironment.buildTypeBindings(LookupEnvironment.java:464)
	at org.eclipse.jdt.internal.compiler.Compiler.internalBeginToCompile(Compiler.java:845)
	at org.eclipse.jdt.internal.compiler.Compiler.processAnnotations(Compiler.java:961)
	at org.eclipse.jdt.internal.compiler.Compiler.compile(Compiler.java:450)
	at org.eclipse.jdt.internal.compiler.Compiler.compile(Compiler.java:426)
	at org.eclipse.jdt.internal.core.builder.AbstractImageBuilder.compile(AbstractImageBuilder.java:386)
	at org.eclipse.jdt.internal.core.builder.BatchImageBuilder.compile(BatchImageBuilder.java:214)
	at org.eclipse.jdt.internal.core.builder.AbstractImageBuilder.compile(AbstractImageBuilder.java:318)
	at org.eclipse.jdt.internal.core.builder.BatchImageBuilder.build(BatchImageBuilder.java:79)
	at org.eclipse.jdt.internal.core.builder.JavaBuilder.buildAll(JavaBuilder.java:262)
	at org.eclipse.jdt.internal.core.builder.JavaBuilder.build(JavaBuilder.java:185)
	at org.eclipse.core.internal.events.BuildManager$2.run(BuildManager.java:833)
	at org.eclipse.core.runtime.SafeRunner.run(SafeRunner.java:45)
	at org.eclipse.core.internal.events.BuildManager.basicBuild(BuildManager.java:220)
	at org.eclipse.core.internal.events.BuildManager.basicBuild(BuildManager.java:263)
	at org.eclipse.core.internal.events.BuildManager$1.run(BuildManager.java:316)
	at org.eclipse.core.runtime.SafeRunner.run(SafeRunner.java:45)
	at org.eclipse.core.internal.events.BuildManager.basicBuild(BuildManager.java:319)
	at org.eclipse.core.internal.events.BuildManager.basicBuildLoop(BuildManager.java:371)
	at org.eclipse.core.internal.events.BuildManager.build(BuildManager.java:392)
	at org.eclipse.core.internal.events.AutoBuildJob.doBuild(AutoBuildJob.java:154)
	at org.eclipse.core.internal.events.AutoBuildJob.run(AutoBuildJob.java:244)
	at org.eclipse.core.internal.jobs.Worker.run(Worker.java:63)
```

Details:

```
eclipse.buildId=4.11.0.I20190307-0500
java.version=1.8.0_162
java.vendor=Oracle Corporation
BootLoader constants: OS=win32, ARCH=x86_64, WS=win32, NL=de_DE
Framework arguments:  -product org.eclipse.epp.package.java.product
Command-line arguments:  -os win32 -ws win32 -arch x86_64 -product org.eclipse.epp.package.java.product
```

The problem persists even after restarting Eclipse.
You can however delete the project in eclipse (not on disk) and reimport it to resolve the NullPointerException.
If you then delete the contents of `.apt_generated` and reimport the project you get the NullPointerException again.

If you delete the Parameter of the Method `de.sanacorp.adas.testenv.TestDslBase.$BigDecimal(long)` the NullPointerException disappears.

If the package that the BigDecimalBuilder is generated into has less than 3 parent packages (for instance `de.sanacorp.abc` instead of `de.sanacorp.abc.defg`) the NullPointerException disappears.
