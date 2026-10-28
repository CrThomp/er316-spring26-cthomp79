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
|D3|Outstanding Fines|BVA|0.00 fine; fine below limit; fine at limit; fine above limit|Checkout allowed below limit; rejected for above limit|This tests if the fine threshold and its boundary|
|D4|Number of Books Checked out|BVA|Below maximum; exactly maximum; above maximum<br />|Checkout allowed below maximum; rejected when maximum reached or exceeded. |Verification of the enforcement on borrowing limits of each user-type|
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
|T3|`testSuspendedPatron`|D2|Available copy >> 0; patron account suspended == true|1.0|No checkout log created; book inventory unchanged|
|T4|`testFineBelowLimit`|D3|Available copy; patron fine below threshold|0.0|Book checked out successfully|
|T5|`testFineAtLimit`|D3|Available copy; patron fine equal to threshold|3.0|Book checked out successfully|
|T6|`testFineAboveLimit`|D3|Available copy; patron fine above threshold|3.0|No checkout; patron and book objects unchanged|
|T7|`testBelowCheckoutLimit`|D4|Patron  currently holds at least one fewer books than maximum|0.0|Checkout succeeds; checkout count increases|
|T8|`testAtCheckoutLimit`|D4|Patron currently holds maximum allowed books|4.0|Checkout rejected; book inventory unchanged|
|T9|`testInvalidPatronID`|D5|Valid book; patronID not registered|1.0|No changes to book or patron records|
|T10|`testSuccessfulCheckout`|D6|Book is available; Patron is valid; Patron isn't suspended; patron does not have outstanding fines|0.0|Inventory count, patron checkout history and checkout count changes|
|T11|`testRejectedCheckout`|D7|Patron is suspended and attempts a checkout|1.0|No checkout record created; inventory left unchanged|

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
|F1|Checkout0|T1|Book should be unavailable after checkout and update inventory|Checkout succeeds, but the book remains available when it should no longer be available|Successful checkout must correctly update book availability and inventory|Inventory records will become inconsistent with actual book checkouts. Other patrons may be allowed to borrow books that should no longer be available|Major - library will provide books that are unavailable to continuously be checked out|
|F2|Checkout1|T2|Patron will have book in checked-out list after checkout|Checkout succeeds, but the patron's checked-out list is not updated to reflect the new book|successful checkout must update patron borrowing records. |Patron account information is inaccurate and borrowing limits may not be correctly enforced|Major - inconsistency in patron records|
|F3|Checkout2|T2|Checkout is rejected with return code 2.0 when no copies are available|The error code provided is  2.0 for the unavailable book when the expected outcome is 0.0|Books with no available copies must not be checked out|System allows a checkout of an unavailable book|Critical - violates a fundamental rule and allows unchecked borrowing|
|||||||||

## Part 4: Comparative engineering judgment

### Implementation summary

Failure count is useful evidence, but it does not determine which implementation is best. One critical defect may matter more than several minor defects.

|Implementation|Distinct defects found|Highest severity|State corruption?|Prohibited operation allowed?|Overall assessment|
|-|-:|-|-|-|-|
|Checkout0|1|Major|Yes|no evidence|Functional but inventory state becomes incorrect after checkout|
|Checkout1|1|Major|Yes|no evidence|functional but patron records become incorrect after checkout|
|Checkout2|1|Critical|Yes|yes|unsafe because it allows invalid checkouts|
|Checkout3|0|none observed|No|No|Best implementation based on current testing|

### Recommended implementation (150–250 words)

Select the implementation you consider best. Support your decision with specific Test IDs and Defect IDs. Compare your selection with at least two alternatives. Explain:

1. why the type, consequence, and severity of its defects are more acceptable than the alternatives;
2. which remaining risks you would accept and which you would not accept;
3. why failure count alone does or does not support your decision; and
4. one additional test that would increase confidence in your recommendation.

**Your response:**



**Based on the above testing and the outcomes of those tests, Checkout3 did not throw any major or critical errors. Although Checkout3 did not produce any errors, Checkout0 would be my recommended implementation. Checkout0 produces the risk of the inventory state becoming incorrect at checkout, where as Checkout1 produces incorrect patron records and Checkout2 produces an unsafe environment that allows invalid checkouts. Checkout3 did not throw any of these same risks or errors, but also had very little feedback information from the gradle test function.** 

**Fixing the code pieces that are producing the incorrect outcomes in Checkout0 would be worth the time to solve. Knowing the issue is with inventory, the book and checkout classes would be the classes requiring updates. Fixing the semantics and testing each changed method would allow the opportunity to fix this inventory error. Fixing the errors in Checkout0 will yield a functioning program with very minimal updates to the code.** 

**For an additional test for black-box testing I would suggest adding a test to check each Patron type (Faculty, Staff, Student, Public, Child) and ensuring each user type is being tested for the proficiency of the inventory management on enforcing max checked out books. Testing all possible outcomes would create a more rounded black-box testing environment without deep diving into the white-box testing implementation.**

