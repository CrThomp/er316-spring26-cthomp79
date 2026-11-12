# Assignment 4 — CI and Static Analysis Report

**Student name:**  Christina Thompson
**ASU ID:**  Cthomp79
**Repository:**  https://github.com/CrThomp/er316-spring26-cthomp79
**Final Dev workflow run:**  
**Verified-build run on main:**

This Markdown file is the complete report. Be specific where requested, but keep your answers concise. Do not create a separate PDF.

## Part 1: CI questions

Keep the complete section to approximately 250–400 words.

1. In the given workflow, what is the relationship among the workflow, its three jobs, each job's runner, and the steps inside a job?
   **Answer:**
   The workflow is the GitHub process defined by the .yml file. The workflow contains instructions to build, test and provide static analysis. Each of these jobs is provided by a separate GitHub runner. Since each job is ran on separate runners they will be isolated from each other and will not share files. Each job will execute steps sequentially on their runner and will use files produced by that job. The workflow will coordinate the execution of jobs and each one will provide a specific portion of the CI process.

2. Why must each job repeat repository checkout and Java setup?
   **Answer:**
   Since each job is ran on a separate runner, the files checked out of the Java installation are configured in one job and are not available to another job. Every job must check out the repository, install Java and initialize the Gradle environment before being able to run its tasks.

3. Why does the workflow use `./gradlew` rather than an unspecified system Gradle installation?
   **Answer:**
   Utilizing the gradle wrapper ensures that every runner uses the exact same Gradle version configured for the project. This will create an environment where the builds, tests and static analysis are reproducible and will mitigate build failures caused by different Gradle versions.
   
   

1. Why is `contents: read` sufficient for this workflow?
   **Answer:**
   The workflow only needs access to the source code from the repository so it can compile, test and analyze the project. The expectation to modify the repository, push commits or change settings is not required. Read-only access to this repository contents is sufficient and protects the build.
   
   

## Part 2: Static-analysis findings

### 2.1 Initial and final results

Record only the total number of findings before and after your changes in Checkout.java. Do not list every finding here.

| Tool       | Initial finding count                                                                                       | Final finding count                                                                                         |
| ---------- | -----------------------------------------------------------------------------------------------------------:| -----------------------------------------------------------------------------------------------------------:|
| Checkstyle | Total files checked: 4<br />Total violations: 135<br />Files with violations: 4                             | Total files checked: 4<br />Total violations: 103<br />Files with violations: 4                             |
| SpotBugs   | Code Size: 414<br />Total Bugs: 9<br />Bugs P1: 3<br />Bugs P2: 6<br />Bugs P3: 0<br />Bugs Experimental: 0 | Code size: 416<br />Total bugs: 8<br />Bugs P1: 2<br />Bugs P2: 6<br />Bugs P3: 0<br />Bugs Experimental: 0 |



### 2.2 Finding decisions

Use one row per finding you investigated in `Checkout.java`. For SpotBugs, include every finding in that file. For Checkstyle, include at least three meaningful findings when available.

| Tool and rule/category | Line number(s)                                      | Why it matters                                                                                                                                                                   | Decision and change                                                                                                                                                                                                                                               |
| ---------------------- | ---------------------------------------------------:| -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| CheckStyle             | main.java.Book.1                                    | Unused code causes unnecessary clutter, making the code harder to understand                                                                                                     | Readable code is necessary when working on a larger project, or in the case another developer will inherit this code. Future selves and developers will be thankful for neater code.                                                                              |
| Checkstyle             | main.java.Book.2                                    | Unused code causes unnecessary clutter, making the code harder to understand                                                                                                     | Readable code is necessary when working on a larger project, or in the case another developer will inherit this code. Future selves and developers will be thankful for neater code.                                                                              |
| CheckStyle             | main.java.Book.143                                  | Line is longer than 120 characters - this creates issues with reading the code. Cleaning this up will make the code easier to understand.                                        | Readable code is necessary when working on a larger project, or in the case another developer will inherit this code. Future selves and developers will be thankful for neater code.                                                                              |
| Checkstyle             | Checkout.java.38                                    | Missing a Javadoc comment - Javadoc comments make the code easier to read and understand                                                                                         | Javadoc comments help make the code readable by providing a summary of the intent of the function. This not only makes the code more readable, but helps organize the code to make sure all methods work efficiently.                                             |
| Checkstyle             | Checkout.java.44                                    | Missing a Javadoc comment - Javadoc comments make the code easier to read and understand                                                                                         | Javadoc comments help make the code readable by providing a summary of the intent of the function. This not only makes the code more readable, but helps organize the code to make sure all methods work efficiently.                                             |
| Checkstyle             | Checkout.java.48                                    | Missing a Javadoc comment - Javadoc comments make the code easier to read and understand                                                                                         | Javadoc comments help make the code readable by providing a summary of the intent of the function. This not only makes the code more readable, but helps organize the code to make sure all methods work efficiently.                                             |
| Checkstyle             | Main.java.8                                         | Missing a Javadoc comment - Javadoc comments make the code easier to read and understand                                                                                         | Did not implement the change - main should be kept unchanged. (For this build, if I had implemented change I would adjust and add Javadoc)                                                                                                                        |
| Checkstyle             | Patron.java.118<br />121<br />122<br />201<br />225 | 'if' or '==' is not followed by a whitespace. The code should all be similar in formatting to other classes.                                                                     | Readable code is necessary when working on a larger project, or in the case another developer will inherit this code. Future selves and developers will be thankful for neater code.                                                                              |
| Checkstyle             | Patron.java.126                                     | Missing a Javadoc comment - Javadoc comments make the code easier to read and understand                                                                                         | Javadoc comments help make the code readable by providing a summary of the intent of the function. This not only makes the code more readable, but helps organize the code to make sure all methods work efficiently.                                             |
| SpotBugs               | Checkout                                            | Comparison of String equality using == or != (or for String Objects) this can yield unfavorable results when it is comparing the reference to the object, not the object itself. | Having code that works predictably and compares the correct objects will provide the appropriate Boolean making the code behave the intended way.                                                                                                                 |
| SpotBugs               | Patron                                              | Class defines equals() and uses Object.hashCode() this is an improper use of the function                                                                                        | Having code that works predictably and compares the correct objects will provide the appropriate Boolean making the code behave the intended way.                                                                                                                 |
| SpotBugs               | Checkout                                            | Test for floating point equality - floating points should be translated into doubles before compared                                                                             | Having floating points changed into doubles will provide a more accurate result.<br />I did not find floating type number comparisons - this was not solved.                                                                                                      |
| SpotBugs               | Checkout.<br />Checkout<br />Patron<br />Patron     | Malicious code vulnerability - returning reference to mutable objects.                                                                                                           | Having returns be accurate with proper reference to objects will provide the code to behave in the expected manner.                                                                                                                                               |
| Spotbugs               | Checkout$Transaction                                | Unread fields are present in the code.                                                                                                                                           | Keeping unread fields to a minimum will not only clean the code, but prevent the compiler from parsing through unused code and dedicating unneeded resources. <br />I'm keeping unread fields as they are methods that may be implemented later for this software |



## Part 3: CI workflow evidence

### 3.1 Completed workflow

1. Were you able to complete the Test and Static Analysis jobs?
   **Answer:**
   Yes, the test and static analysis jobs were completed by using Gradle, Checkstyle and Spotbugs and I had implemented the changes suggested by these tools.

2. Did the Static Analysis job pass? Explain what happened in its Checkstyle and SpotBugs steps and why.
   **Answer:**
   Checkstyle still reports violations, implying that static analysis did not fully pass. Spotbugs also still reports bugs in the Checkout.java file.
   
   

### 3.2 Workflow-run diagnosis

Provide information from your final run. Build and Test must pass. Static Analysis must run and upload its reports, but it may still report a failed job.

**Run URL:** [CI quality checks · CrThomp/er316-spring26-cthomp79@a3b39e7](https://github.com/CrThomp/er316-spring26-cthomp79/actions/runs/43289200639)
**Commit:**  a3b39e7
**Jobs that ran:** Build and Test, Static Analysis, Add checkout, Set up Java, Gradle permission
**Which jobs passed, which failed, and why?**

All jobs passed, the static analysis job passed with the implemented changes from task 2. 



## Part 4: Integration into Dev

Provide a short summary of how you merged the branches and what problems or conflicts you encountered.

First I switched to the Blackbox branch, from Blackbox I created the Dev branch. From Dev I merged review and cleared any errors/committed changes and pushed to origin. I then merged StaticAnalysis to Dev. I ran ./gradlew clean test as instructed before merging Dev into main and pushing it to the repository.



## Part 5: Verified build

**Workflow-run URL:**  [Reformatting... again · CrThomp/er316-spring26-cthomp79@2755fdc](https://github.com/CrThomp/er316-spring26-cthomp79/commit/2755fdc5a10bd140c2cfa4eb121783017546276c)
**Artifact name:**  verified-application-distribution
verified-test-report
**Command used to run the extracted application:**./er316-spring26-cthomp79/bin/er316-spring26-cthomp79  
**Did the application start successfully?** No, my application did not start successfully, I had to manually build it from the github actions page. 

Why should the `package` job depend on the `test` job?
The package should depend on the test job because the package should not build unless all the unit tests pass. Having this be a dependant with the needs function creates the environment where faulty code/faulty meanings are not packaged and distributed. 

### Optional scheduled-build research (extra credit)

**Scheduled run URL:**  [Verified application build · CrThomp/er316-spring26-cthomp79@2755fdc](https://github.com/CrThomp/er316-spring26-cthomp79/actions/runs/43294674860)
**Commit that disables/removes the five-minute schedule afterward:** 98dcfd9

The expression I researched: cron: '2-59/5 * * * * '

The purpose of this expression is to schedule the workflow to run every five minutes starting at minute 2 of each hour. 
This scheduler is meant to work from the main branch because GitHub actions needs to use the version maintained on the main branch over featured branches. GitHub may start the workflow late depending on server requests especially if it scheduled at the beginning of the hour. Running the workflow every 5 minutes will consume GitHubs resources unneededly. The 5-minute schedule should not remain enabled as it will use these resources. 

## Part 6: Engineering reflection

Write 175–275 words. Address all six prompts:

1. Which finding or CI failure had the greatest potential impact, and why?
2. How did tests, Checkstyle, and SpotBugs provide different evidence?
3. What does the final `Dev` CI run establish, and what does it not establish?
4. What one improvement would you make to this CI process on a real team?
5. How does the packaging job prevent a ZIP with failing tests from being published?
6. Why is static analysis not a prerequisite for the packaging job in this assignment?

**Response:**

In the CI failures, the greatest potential impact were when unit tests failed. When the unit tests fail, it indicates that the applications functionality is not working as expected and can create incorrect behavior or crashes. Evidence throughout the static analysis process were supplied by the various tasks, in these tasks tests verified the application behaved as expected, checkstyle identified coding style violations and spotbugs analyzed the code for potential bugs and programming mistakes. The final Dev CI run establisehd the code on the Dev branch passed the configured checks at the time of the run, it does not provide that the application is bug-free or compatible with all environments. The CI run on the Dev branch also shows as an "in this moment and time," report, not any reports for future changes or builds. Based on the test results, implemented changes and further suggestions through the explored tools, an improvement I would make to this CI process is adding integration tests. Integration tests will provide more insight on how different parts of the application work together. The packaging job is only ran if the test portion passes, which prevents the packaging and publishing of an artifact if the tests are failing. Static analysis is not a prereqisite for the packaging job because the assumption is the tests are broad enough and accurate enough to provide a finished product. In a real world environment, static analysis might be added as a prerequisite for the packaging and publishing jobs in this or similar scenarios. 


