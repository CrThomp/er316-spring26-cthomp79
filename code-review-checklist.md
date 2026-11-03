# Assignment 3 Code Review and Fix Record

**Student name:** Christina Thompson
**ASU ID:** Cthomp79 
**Review branch:** `Review`  
**Blackbox commit used as the branch point:** c22b705941e7740512b369a51ac9170497dee3c9 

## AI-use statement

Choose one and complete it:

* \[x] I did not use generative AI for this report.
* \[ ] I used generative AI only for permitted grammar/table-formatting help. Tool and exact use: \[describe].

The findings, evidence, severity judgments, fixes, and traceability decisions below are my own work.

## Categories and severity

* **CR - Correctness/functional:** behavior can be wrong, unsafe, or inconsistent with the specification.
* **MA - Maintainability/design:** duplication, excessive coupling, poor structure, or another obstacle to safe change.
* **CS - Coding standard/readability:** naming, formatting, documentation, or clarity problem.
* **Critical:** likely data loss, serious security problem, or system-wide failure.
* **High:** major behavior is wrong or a common operation fails.
* **Medium:** limited behavioral problem or meaningful maintenance risk.
* **Low:** localized readability, consistency, or minor maintenance problem.

|ID|File and line(s)|Category|Concrete evidence|Impact and severity rationale|Focused correction|
|-|-|-|-|-|-|
|CR1|`Book.java:106-107`|CR|The returnBook function allows the amount of available copies to become any number < 100, regardless of total copies. |High: major behavior is wrong, the inventory of available copies can supersede the total copies amount. |Compare the number to the int variable of totalCopies:<br />if (availableCopies < totalCopies)<br />|
|CR2|'Checkout.java:246'|CR|The comparison of strings with == compares the references, not necessarily the string itself. This can throw a false Boolean even when the strings match.|Medium: limited behavior problem or meaningful maintenance risk, the wrong patron type may be able to checkout an incorrect number of books. |Change the comparison symbols from "==" to ".equals()" notation so the correct comparison can take place.|
|MA1|'Book.java:13\&16'|MA|Boolean available is redundant when the comparison of availableCopies > 0 could be used. |Low: replacing the Boolean would not change the function of the code, only increase the maintainability by decreasing the chance of the wrong variable being used. |Replace all calls to the Boolean available with the statement availableCopies > 0|
|CS1|'Patron.java:191-197'|CS|This method affects the codes readability by providing an if/else statement for something that could be compared as a Boolean to be returned.|Low: replacing the method would not change the function of the code, only increase the readability of the code.|Replace the function with the simple statement:<br />return bookMap.containsKey(isbn);|

## Review findings

Record exactly four distinct findings. Include at least one CR, one MA, and one CS finding. The fourth may use any category.

|ID|File and current line(s)|Category|Concrete evidence from the code|Impact and severity rationale|Focused correction|
|-|-|-|-|-|-|
|D1||||||
|D2||||||
|D3||||||
|D4||||||

## Selected fixes and traceability

Select two findings, including at least one CR finding. Each change should have its own GitHub issue and focused commit.

|Defect ID|GitHub issue link|Fix commit hash/link|What changed|
|-|-|-|-|
|||||
|||||

## Fix rationale

For each fix, explain in one or two specific sentences why the change makes the codebase better.

**Fix 1:** \[response]

**Fix 2:** \[response]

