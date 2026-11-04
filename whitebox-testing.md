# Assignment 3 White-Box Testing Report

**Student name:** [Your name]  
**ASU ID:** [Your ASU ID]  
**Whitebox branch:** `Whitebox`  
**Commit represented by this report:** [commit hash]

## AI-use statement

Choose one and complete it:

- [ ] I did not use generative AI for this report or implementation.
- [ ] I used generative AI only for permitted grammar/table-formatting help. Tool and exact use: [describe].

The CFG, test design, tests, implementation, coverage interpretation, and reflection are my own work.

## 1. checkoutBook implementation evidence

| Evidence | Commit hash/link or result |
|---|---|
| Commit that configures my Assignment 2 tests to run against `Checkout.class` | |
| Initial `./gradlew blackBoxTest` result before implementation | |
| Commit containing my `checkoutBook()` implementation | |
| Final `blackBoxTest` result | |
| Final `whiteBoxTest` result | |

## 2. Control-flow graph for countBooksByType

Embed your CFG image below. The graph must show entry/exit, executable statement groups, decision outcomes, loop entry/back/exit behavior, and the early return. Use current source line numbers or short statement labels consistently.

![Control-flow graph for countBooksByType](docs/countBooksByType-cfg.png)

### Node legend

| Node | Source line(s) or statement | Role in control flow |
|---|---|---|
| N1 | | |
| N2 | | |
| N3 | | |
| Add or remove rows as needed | | |

## 3. Coverage-to-test design

Add as many rows as your design requires; there is no fixed minimum. Collectively, the rows must cover every CFG node and edge. Inputs must be concrete enough that another person could implement the test without guessing.

| Case | Targeted node/edge sequence or decision outcome | Concrete inventory setup and method inputs | Expected return | JUnit test method | Why this case is necessary |
|---|---|---|---:|---|---|
| WB1 | | | | | |
| WB2 | | | | | |
| Add or remove rows as needed | | | | | |

### Coverage argument

Identify where your table covers each decision's true and false outcomes and the loop's zero-iteration, iteration/back-edge, and exit behavior. If one test covers several items, name that test rather than repeating its setup.

[Your response]

## 4. JaCoCo results

Command used: `./gradlew clean test jacocoTestReport`

Record the final values from `build/reports/jacoco/test/html/index.html`.

| Scope | Line coverage | Branch coverage | What this scope tells you |
|---|---:|---:|---|
| Whole project | | | |
| `Checkout` class | | | |
| `Checkout.countBooksByType()` | | | |

**Does `countBooksByType()` have complete branch coverage?** [Yes/No]

If no, identify the exact uncovered decision outcome and specify the additional setup, input, and expected result needed to cover it. If yes, identify the test methods responsible for the last decision outcome you covered.

[Your response]

## 5. Evidence-based analysis and reflection

### Structural insight

Name one test that you designed because you inspected the implementation structure rather than only the JavaDoc. Identify the relevant node/edge and explain why black-box analysis alone might not have suggested it.

[Your response]

### Interpreting coverage

What does your JaCoCo evidence support about your tests? What can it **not** establish about correctness? Refer to at least one percentage and one named test or uncovered condition.

[Your response]

### Black-box compared with white-box testing

Give one concrete contribution from each approach in this assignment. Which combination gave you more confidence in `Checkout`, and why?

[Your response]

### Remaining risk

Identify one realistic defect or interaction that your current tests might still miss. Explain why it remains outside or weakly represented in your test suite.

[Your response]

## 6. Final submission check

- [ ] The report identifies the exact graded commit.
- [ ] The CFG image renders on GitHub.
- [ ] Every designed case maps to an implemented JUnit method.
- [ ] Student black-box, white-box, and complete test tasks run.
- [ ] JaCoCo values are copied from the final report.
- [ ] All placeholders and instructional rows have been removed.
- [ ] The completed file, CFG image, implementation, and tests are pushed on `Whitebox`.
