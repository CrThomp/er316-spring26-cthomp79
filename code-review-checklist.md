# Assignment 3 Code Review and Fix Record

**Student name:** [Your name]  
**ASU ID:** [Your ASU ID]  
**Review branch:** `Review`  
**Blackbox commit used as the branch point:** [commit hash]

## AI-use statement

Choose one and complete it:

- [ ] I did not use generative AI for this report.
- [ ] I used generative AI only for permitted grammar/table-formatting help. Tool and exact use: [describe].

The findings, evidence, severity judgments, fixes, and traceability decisions below are my own work.

## Categories and severity

- **CR - Correctness/functional:** behavior can be wrong, unsafe, or inconsistent with the specification.
- **MA - Maintainability/design:** duplication, excessive coupling, poor structure, or another obstacle to safe change.
- **CS - Coding standard/readability:** naming, formatting, documentation, or clarity problem.
- **Critical:** likely data loss, serious security problem, or system-wide failure.
- **High:** major behavior is wrong or a common operation fails.
- **Medium:** limited behavioral problem or meaningful maintenance risk.
- **Low:** localized readability, consistency, or minor maintenance problem.

## Example row - delete before submitting

This example is fictional and is not a finding from the assignment code.

| ID | File and line(s) | Category | Concrete evidence | Impact and severity rationale | Focused correction |
|---|---|---|---|---|---|
| EX | `Example.java:12` | CS | Local variable `x1` does not communicate what value it stores. | Low: behavior is unaffected, but the name slows review and maintenance. | Rename it to describe its role and update its uses. |

## Review findings

Record exactly four distinct findings. Include at least one CR, one MA, and one CS finding. The fourth may use any category.

| ID | File and current line(s) | Category | Concrete evidence from the code | Impact and severity rationale | Focused correction |
|---|---|---|---|---|---|
| D1 | | | | | |
| D2 | | | | | |
| D3 | | | | | |
| D4 | | | | | |

## Selected fixes and traceability

Select two findings, including at least one CR finding. Each change should have its own GitHub issue and focused commit.

| Defect ID | GitHub issue link | Fix commit hash/link | What changed |
|---|---|---|---|
| | | | |
| | | | |

## Fix rationale

For each fix, explain in one or two specific sentences why the change makes the codebase better.

**Fix 1:** [response]

**Fix 2:** [response]

