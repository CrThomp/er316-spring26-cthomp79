# Black-Box Testing Report — Assignment 2

**Student name:**  Christina Thompson
**ASU ID:**  cthomp79
**Date:10/28/2026**

This file is the complete assignment report. Keep answers concise and specific. Do not create a separate PDF.

## AI-use declaration

The technical work (all the text/code) in this report and in the test suite must be your own. AI may be used only to format content you have already written into Markdown or to repair Markdown syntax. AI may not identify conditions, select partitions or boundary values, determine expected behavior, design or write tests, interpret failures, assign severity, or recommend an implementation.

If you use AI for formatting, use this prompt or an equivalent prompt with the same restrictions:

> Format the text below into the supplied Markdown table columns. Do not add, remove, correct, infer, reinterpret, or suggest any technical content. Preserve uncertainty and blanks exactly. If information needed by a column is missing, leave that cell blank and list the missing cells after the table. Do not evaluate whether the testing content is correct. Do not change the student's wording or technical conclusions.

Select one and complete the details:

* \[x] I did not use AI for this assignment.
* \[ ] I used AI only for permitted Markdown formatting or syntax correction. Tool used: \_\_\_\_\_\_\_\_\_\_. Section(s) formatted: \_\_\_\_\_\_\_\_\_\_.

I verified that the submitted technical content and code represent my own reasoning. **Initials:** CT



## Part 1: Test conditions — EP and BVA

Summarize how you divided the specification into testable conditions. Use **EP** for equivalence partitioning, **BVA** for boundary value analysis, or **EP/BVA** when both apply.

Use the specification to discover the important categories. Consider:

* the method's inputs and relevant object states;
* numeric or count thresholds;
* interactions between conditions and validation order; and
* observable state after successful and rejected operations.

There is no required number of rows. Include enough rows to demonstrate systematic coverage. For each row, explain why the selected partitions or values are representative.

|Design ID|Condition|Technique|Values or partitions selected|Expected result or behavior|Why this condition matters|
|-|-|-|-|-|-|
|D1|Book availability|EP|Available; unavailable|Successful checkout when other conditions permit; rejection when no copy is available|Demonstrates how two input states form different behavioral groups|
|D2|Patron Suspended|EP|Patron Eligible; Patron Suspended|Eligible Patron is allowed to proceed, suspended patron cannot complete checkout|Separates if a Patron is valid for borrowing or invalid<br />|
|D3|Outstanding Fines|BVA|0.00 fine; fine below limit; fine at limit; fine above limit|Checkout allowed below limit; rejected for at or above limit|This tests if the fine threshold and its boundary|
|D4|Number of Books Checked out|BVA|Below maximum (Faculty); exactly maximum (Faculty); above maximum (Faculty);<br />Below maximum (Staff); exactly maximum (Staff); above maximum (Staff); Below maximum (Student); exactly maximum (Student); above maximum (Student);<br />Below maximum (Public); exactly maximum (Public); above maximum (Public);<br />Below maximum (Child); exactly maximum (Child); above maximum (Child)|Checkout allowed below maximum; rejected when maximum reached or exceeded. (In all user-type cases) |Verification of the enforcement on borrowing limits of each user-type|
|D5|Patron ID is valid|EP|Valid patronID; nonexistent or invalid patronID|Valid patronID processed; invalid patronID rejected|Confirms patron is valid before checkout processing|
|D6|Successful Checkout|EP|Successful transaction|Available book count decreases; checked out record is created|Confirms correct observable state after successful checkout|
|D7|Rejected Checkout|EP |Failed transaction due to any validation error|No copy count changes; no checked out record created|Ensures rejected checkout has no unintended side effects|

## 

## Part 2: Test traceability

Connect every implemented test to the design reasoning above. One parameterized method may represent several Test IDs or expected results.

Your suite must cover:

* every return code in the `checkoutBook()` JavaDoc;
* every required condition category in Part 1;
* at least two validation-priority situations;
* state changes for an ordinary checkout and a renewal;
* unchanged book and patron state for at least two rejected operations.

There is no fixed minimum number of test methods.

|Test ID|JUnit method and case|Design ID(s)|Exact input or setup|Expected return|Expected observable state|
|-|-|-|-|-:|-|
|T1|`testBookAvailable`|D1|One available copy; registered eligible patron; no warnings|0.0|Available copies decrease by one; patron receives the book; checkout count becomes one|
|T2|`testUnavailableBook`|D1|Available copies = 0; otherwise eligible registered patron|2.0|Book and patron remain unchanged|
|T3||||||

## Part 3: Defect evidence

Run the same tests against `Checkout0`, `Checkout1`, `Checkout2`, and `Checkout3`. Analyze **distinct behavioral defects**, not merely failed tests. Several failed tests may reveal the same defect.

Use only behavior you can observe. Because this is black-box testing, describe the specification rule that appears to be broken; do not claim to know the hidden source code.

Severity guide:

* **Critical:** permits a prohibited operation, seriously corrupts state, or creates a serious safety/security risk.
* **Major:** causes incorrect business behavior or persistent data inconsistency.
* **Moderate:** produces an incorrect result with limited impact or an available workaround.
* **Minor:** has low impact and does not substantially affect the checkout decision or state.

|Defect ID|Implementation(s)|Revealing Test ID(s)|Expected behavior|Observed behavior|Specification rule apparently violated|Consequence|Severity and justification|
|-|-|-|-|-|-|-|-|
|F1||||||||

## Part 4: Comparative engineering judgment

### Implementation summary

Failure count is useful evidence, but it does not determine which implementation is best. One critical defect may matter more than several minor defects.

|Implementation|Distinct defects found|Highest severity|State corruption?|Prohibited operation allowed?|Overall assessment|
|-|-:|-|-|-|-|
|Checkout0||||||
|Checkout1||||||
|Checkout2||||||
|Checkout3||||||

### Recommended implementation (150–250 words)

Select the implementation you consider best. Support your decision with specific Test IDs and Defect IDs. Compare your selection with at least two alternatives. Explain:

1. why the type, consequence, and severity of its defects are more acceptable than the alternatives;
2. which remaining risks you would accept and which you would not accept;
3. why failure count alone does or does not support your decision; and
4. one additional test that would increase confidence in your recommendation.

**Your response:**

