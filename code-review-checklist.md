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
  
  

## Review findings

Record exactly four distinct findings. Include at least one CR, one MA, and one CS finding. The fourth may use any category.

| ID  | File and current line(s) | Category | Concrete evidence from the code                                                                                                                           | Impact and severity rationale                                                                                                                                      | Focused correction                                                                                        |
| --- | ------------------------ | -------- | --------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------ | --------------------------------------------------------------------------------------------------------- |
| D1  | `Book.java:106-107`      | CR       | The returnBook function allows the amount of available copies to become any number < 100, regardless of total copies.                                     | High: major behavior is wrong, the inventory of available copies can supersede the total copies amount.                                                            | Compare the number to the int variable of totalCopies:<br />if (availableCopies < totalCopies)<br />      |
| D2  | 'Checkout.java:246'      | CR       | The comparison of strings with == compares the references, not necessarily the string itself. This can throw a false Boolean even when the strings match. | Medium: limited behavior problem or meaningful maintenance risk, the wrong patron type may be able to checkout an incorrect number of books.                       | Change the comparison symbols from "==" to ".equals()" notation so the correct comparison can take place. |
| D3  | 'Book.java:13&16'        | MA       | Boolean available is redundant when the comparison of availableCopies > 0 could be used.                                                                  | Low: replacing the Boolean would not change the function of the code, only increase the maintainability by decreasing the chance of the wrong variable being used. | Replace all calls to the Boolean available with the statement availableCopies > 0                         |
| D4  | 'Patron.java:191-197'    | CS       | This method affects the codes readability by providing an if/else statement for something that could be compared as a Boolean to be returned.             | Low: replacing the method would not change the function of the code, only increase the readability of the code.                                                    | Replace the function with the simple statement:<br />return bookMap.containsKey(isbn);                    |

## Selected fixes and traceability

Select two findings, including at least one CR finding. Each change should have its own GitHub issue and focused commit.

| Defect ID | GitHub issue link                                           | Fix commit hash/link                     | What changed                                                    |
| --------- | ----------------------------------------------------------- | ---------------------------------------- | --------------------------------------------------------------- |
| D1        | https://github.com/CrThomp/er316-spring26-cthomp79/issues/2 | c33cb603f1c95b0431cac8caf1998c750608935e | updated Book.java line 106 to fix the issue                     |
| D2        | https://github.com/CrThomp/er316-spring26-cthomp79/issues/3 | 91bf41b1d039efe15b146294571b8bf29af90d46 | updated Checkout.java line 246 to correct comparison for String |
| D3        | https://github.com/CrThomp/er316-spring26-cthomp79/issues/4 |                                          |                                                                 |
| D4        | https://github.com/CrThomp/er316-spring26-cthomp79/issues/5 | adff7e354bed8c5e456198d590e34bea34d47cda | Condensed function for readability                              |

## Fix rationale

For each fix, explain in one or two specific sentences why the change makes the codebase better.

**Fix 1:** \Fixing D1 provides a more accurate inventory system. Returning books before allowed for an unintended consequence of up to 100 available copies to be recorded, regardless of the total copies of the book. This fix will allow and limit the available copies to the real inventory number of total copies. 

**Fix 2:** \Fixing D2 allows for a more accurate comparison of the String object, not the reference to the object. This is important because it is comparing the type of Patron, which will limit and allow the patron to either checkout more or less books. This change supports a more functional inventory management system. 

Fix 3: Fixing D3 will make the code more maintainable, for this exercise I did not resolve the issue but still set up the issue in github. Github is a wonderful tool I intend to familiarize myself with and am taking the opportunity to build additional cases to allow this growth.
Fix 4:\Fixing D4 allows this code to be more readable. Having a return be a boolean statement over if/else brackets will help allow other developers to read the intentions of the function. This change will improve the understanding of the function and keep the code tidy. 
