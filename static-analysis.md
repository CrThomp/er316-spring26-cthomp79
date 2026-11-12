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

|Tool|Initial finding count|Final finding count|
|-|-:|-:|
|Checkstyle|Total files checked: 4<br />Total violations: 135<br />Files with violations: 4|Total files checked: 4<br />Total violations: 103<br />Files with violations: 4|
|SpotBugs|Code Size: 414<br />Total Bugs: 9<br />Bugs P1: 3<br />Bugs P2: 6<br />Bugs P3: 0<br />Bugs Experimental: 0|Code size: 416<br />Total bugs: 8<br />Bugs P1: 2<br />Bugs P2: 6<br />Bugs P3: 0<br />Bugs Experimental: 0|



### 2.2 Finding decisions

Use one row per finding you investigated in `Checkout.java`. For SpotBugs, include every finding in that file. For Checkstyle, include at least three meaningful findings when available.

|Tool and rule/category|Line number(s)|Why it matters|Decision and change|
|-|-:|-|-|
|CheckStyle|main.java.Book.1|Unused code causes unnecessary clutter, making the code harder to understand|Readable code is necessary when working on a larger project, or in the case another developer will inherit this code. Future selves and developers will be thankful for neater code.|
|Checkstyle|main.java.Book.2|Unused code causes unnecessary clutter, making the code harder to understand|Readable code is necessary when working on a larger project, or in the case another developer will inherit this code. Future selves and developers will be thankful for neater code.|
|CheckStyle|main.java.Book.143|Line is longer than 120 characters - this creates issues with reading the code. Cleaning this up will make the code easier to understand.|Readable code is necessary when working on a larger project, or in the case another developer will inherit this code. Future selves and developers will be thankful for neater code.|
|Checkstyle|Checkout.java.38|Missing a Javadoc comment - Javadoc comments make the code easier to read and understand|Javadoc comments help make the code readable by providing a summary of the intent of the function. This not only makes the code more readable, but helps organize the code to make sure all methods work efficiently.|
|Checkstyle|Checkout.java.44|Missing a Javadoc comment - Javadoc comments make the code easier to read and understand|Javadoc comments help make the code readable by providing a summary of the intent of the function. This not only makes the code more readable, but helps organize the code to make sure all methods work efficiently.|
|Checkstyle|Checkout.java.48|Missing a Javadoc comment - Javadoc comments make the code easier to read and understand|Javadoc comments help make the code readable by providing a summary of the intent of the function. This not only makes the code more readable, but helps organize the code to make sure all methods work efficiently.|
|Checkstyle|Main.java.8|Missing a Javadoc comment - Javadoc comments make the code easier to read and understand|Did not implement the change - main should be kept unchanged. (For this build, if I had implemented change I would adjust and add Javadoc)|
|Checkstyle|Patron.java.118<br />121<br />122<br />201<br />225|'if' or '==' is not followed by a whitespace. The code should all be similar in formatting to other classes.|Readable code is necessary when working on a larger project, or in the case another developer will inherit this code. Future selves and developers will be thankful for neater code.|
|Checkstyle|Patron.java.126|Missing a Javadoc comment - Javadoc comments make the code easier to read and understand|Javadoc comments help make the code readable by providing a summary of the intent of the function. This not only makes the code more readable, but helps organize the code to make sure all methods work efficiently.|
|SpotBugs|Checkout|Comparison of String equality using == or != (or for String Objects) this can yield unfavorable results when it is comparing the reference to the object, not the object itself.|Having code that works predictably and compares the correct objects will provide the appropriate Boolean making the code behave the intended way.|
|SpotBugs|Patron|Class defines equals() and uses Object.hashCode() this is an improper use of the function|Having code that works predictably and compares the correct objects will provide the appropriate Boolean making the code behave the intended way.|
|SpotBugs|Checkout|Test for floating point equality - floating points should be translated into doubles before compared|Having floating points changed into doubles will provide a more accurate result.<br />I did not find floating type number comparisons - this was not solved.|
|SpotBugs|Checkout.<br />Checkout<br />Patron<br />Patron|Malicious code vulnerability - returning reference to mutable objects.|Having returns be accurate with proper reference to objects will provide the code to behave in the expected manner.|
|Spotbugs|Checkout$Transaction|Unread fields are present in the code.|Keeping unread fields to a minimum will not only clean the code, but prevent the compiler from parsing through unused code and dedicating unneeded resources. <br />I'm keeping unread fields as they are methods that may be implemented later for this software|



## Part 3: CI workflow evidence

### 3.1 Completed workflow

1. Were you able to complete the Test and Static Analysis jobs?

   **Answer:**
Yes, the test and static analysis jobs were completed by using Gradle, Checkstyle and Spotbugs and implementing changes suggested by these tools.

2. Did the Static Analysis job pass? Explain what happened in its Checkstyle and SpotBugs steps and why.

   **Answer:**
Checkstyle still reports violations, implying that static analysis did not fully pass. Spotbugs also still reports bugs in the Checkout.java file.



### 3.2 Workflow-run diagnosis

Provide information from your final run. Build and Test must pass. Static Analysis must run and upload its reports, but it may still report a failed job.

**Run URL:**  https://github.com/CrThomp/er316-spring26-cthomp79/actions/runs/43140721385/job/130926750216
**Commit:**  6f287b
**Jobs that ran:** Build and Test, Static Analysis
**Which jobs passed, which failed, and why?**

Build and Static Analysis passed, Test failed with Error: Process completed with exit code 127.



## Part 4: Integration into Dev

Provide a short summary of how you merged the branches and what problems or conflicts you encountered.

First I switched to the Blackbox branch, from Blackbox I created the Dev branch. From Dev I merged review and cleared any errors/committed changes and pushed to origin. I then merged StaticAnalysis to Dev. I ran ./gradlew clean test as instructed before merging Dev into main and pushing it to the repository.



## Part 5: Verified build

**Workflow-run URL:**  
**Artifact name:**  
**Command used to run the extracted application:**  
**Did the application start successfully?**

Why should the `package` job depend on the `test` job?

### Optional scheduled-build research (extra credit)

**Scheduled run URL:**  
**Commit that disables/removes the five-minute schedule afterward:**

Record the cron expression you researched. Explain why the scheduled run uses the default branch, why GitHub may start it late, and why the five-minute schedule should not remain enabled:

## Part 6: Engineering reflection

Write 175–275 words. Address all six prompts:

1. Which finding or CI failure had the greatest potential impact, and why?
2. How did tests, Checkstyle, and SpotBugs provide different evidence?
3. What does the final `Dev` CI run establish, and what does it not establish?
4. What one improvement would you make to this CI process on a real team?
5. How does the packaging job prevent a ZIP with failing tests from being published?
6. Why is static analysis not a prerequisite for the packaging job in this assignment?

**Response:**

