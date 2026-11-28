# Software Engineering – Software Design
## Name : Md Mahmudur Rahman ID : IT24024 
# Software Engineering (SE) — OBE-Based Question Paper

**Unit:** Software Design  
**Level:** Undergraduate, aligned to selected BNQF Level 7 descriptors for a four-year bachelor’s programme  
**Time:** 1 hour · **Full marks:** 40

## Assessment instructions

Answer all six questions. Show your working for metric calculations and label the roles and relationships in any diagram. Use brief, case-specific explanations. The suggested times include 5 minutes for reading and review.

## Course outcomes

The five COs below are proposed for this paper. Use the approved course outcome wording and numbering if supplied by the department. On successful completion of the assessed unit, students should be able to:

| CO | Measurable course outcome | Evidence in this paper |
|---|---|---|
| CO1 | Define core software design terms and explain how coupling and cohesion affect maintainability. | Definitions and a cause-and-effect explanation |
| CO2 | Apply an object-oriented design metric to a class model. | A worked CK LCOM calculation |
| CO3 | Analyze design dependencies and identify where an interface mismatch should be resolved. | A traced dependency and analysis of change impact |
| CO4 | Evaluate alternative designs against stated quality criteria and justify a selection. | A case-specific comparison and recommendation |
| CO5 | Create an object-oriented design that supports interchangeable behavior and satisfies given requirements. | A labelled class design and interaction description |

## Question paper

### Q1 · Remember (Knowledge) · CO1 · 4 marks

Define **coupling** and **cohesion** in object-oriented software design. **[2 marks each]**

### Q2 · Understand (Comprehension) · CO1 · 6 marks

A `CourseService` validates enrolments, formats receipts, and sends SMS alerts. It directly calls a third-party SMS gateway. Explain why it is preferable to keep closely related responsibilities together **[3]** and why reducing direct dependencies on external classes can make changes easier **[3]**. Use the example to explain each point.

### Q3 · Apply · CO2 · 7 marks

The table lists every instance field accessed by each method of a class.

| Method | Instance fields accessed |
|---|---|
| M1 | `studentId` |
| M2 | `studentId`, `courseId` |
| M3 | `receiptNumber` |
| M4 | `receiptNumber` |

Apply the Chidamber–Kemerer LCOM rule. Use unordered method pairs. Let P contain pairs that share no instance field and Q contain pairs that share at least one instance field. Use **LCOM = max(|P| − |Q|, 0)**.

1. Group all six method pairs into P and Q. **[4]**
2. State both counts and calculate LCOM. **[3]**

### Q4 · Analyze · CO3 · 7 marks

A campus application expects all notification providers to implement `Notifier.send(recipient, message)`. An existing vendor library instead exposes `TextGateway.push(message, phoneNumber)`. Two application services each call the vendor library directly and reorder the arguments.

1. Trace the current dependencies from both services to the vendor and explain how a change to the vendor method would propagate. **[3]**
2. Analyze how introducing an Adapter would redistribute those dependencies. Identify which classes would depend on the `Notifier` interface and which class would depend on `TextGateway`. Explain where the argument translation would occur. **[4]**

### Q5 · Evaluate · CO4 · 8 marks

A registration portal must check prerequisites, reserve a seat, and record enrolment in that order. Two screens now invoke the workflow, and more clients are expected. The administration team must also be able to inspect seat availability independently.

- **Design A.** Each client directly coordinates the three subsystem services.
- **Design B.** Clients call one registration operation through a Facade. The administration team can still query the seat service directly.

Compare both designs against **workflow consistency** and **ease of changing the workflow** **[4]**. Explain one trade-off of your preferred design **[2]**. Recommend a design and justify how it accounts for the independent administration query **[2]**.

### Q6 · Create · CO5 · 8 marks

Design a fee calculator using the **Strategy** pattern. Regular students pay the full base fee, scholarship students pay 50%, and exchange students pay a flat BDT 2,000. The application must be able to switch fee policies on the same calculator instance at runtime. A new fee policy must be addable without modifying the calculator class.

1. Draw a labelled class diagram or write an equivalent structured class design. Show the calculator, the common fee-policy interface, the three concrete policies, their relationships, and how the active policy is supplied or changed. **[5]**
2. Show the call sequence or pseudocode for calculating fees on the same calculator with a BDT 3,000 base fee, first with the regular policy and then with the scholarship policy. Explain how a fourth policy would be added. **[3]**

## OBE assessment blueprint

Knowledge and Understanding correspond to Remember and Understand in the revised Bloom cognitive taxonomy. Each question is mapped to the primary cognitive process required for its response.

| Question | Revised Bloom level | Primary CO | Marks | Share | Suggested minutes |
|---|---|---|---:|---:|---:|
| Q1 | C1 Remember | CO1 | 4 | 10% | 4 |
| Q2 | C2 Understand | CO1 | 6 | 15% | 7 |
| Q3 | C3 Apply | CO2 | 7 | 17.5% | 9 |
| Q4 | C4 Analyze | CO3 | 7 | 17.5% | 10 |
| Q5 | C5 Evaluate | CO4 | 8 | 20% | 11 |
| Q6 | C6 Create | CO5 | 8 | 20% | 14 |
| **Total** | **All six cognitive levels** | **Five COs** | **40** | **100%** | **55** |

### CO marks and attainment

| CO | Questions | Maximum marks |
|---|---|---:|
| CO1 | Q1, Q2 | 10 |
| CO2 | Q3 | 7 |
| CO3 | Q4 | 7 |
| CO4 | Q5 | 8 |
| CO5 | Q6 | 8 |
| **Total** | **Q1–Q6** | **40** |

For each student, calculate **CO score (%) = marks earned on questions mapped to that CO ÷ maximum marks for that CO × 100**. For cohort attainment, report the percentage of assessed students meeting the department’s approved threshold for each CO.

## BNQF alignment

| Selected Level 7 Fundamental Skills expectation | Assessment evidence |
|---|---|
| Demonstrate knowledge and critical understanding of established principles in the field. | Q1–Q2 assess recall and explanation of coupling, cohesion, and their impact on maintenance. |
| Apply concepts and principles beyond the context in which they were first studied, and use knowledge and skills to solve problems. | Q3 applies a quality metric to a class model. Q4 analyzes a vendor integration. Q6 constructs a design for a campus fee calculator. |
| Critically evaluate the appropriateness of different approaches to solving a problem. | Q5 requires a criteria-based comparison and a justified design decision. |

This written paper samples the cognitive evidence specified above. The alignment covers selected descriptors within a broader qualification framework.

## References

- Course lecture slides, *4-SoftEng-Design.ppt*. Topics used include coupling and cohesion, CK LCOM, Adapter, Facade, and Strategy.
- Anderson, L. W., & Krathwohl, D. R. (Eds.). (2001). *A taxonomy for learning, teaching, and assessing: A revision of Bloom’s taxonomy of educational objectives*. Longman.
- Ministry of Education, Bangladesh. *Bangladesh National Qualifications Framework*, Appendix 4, Level 7, Fundamental Skills, four-year and five-year bachelor’s descriptors. [Official BNQF framework](https://shed.gov.bd/sites/default/files/files/shed.portal.gov.bd/publications/f1c410ad_3af2_462c_a8ae_df1ffdb27905/daf6821b-666e-44e4-85fe-6a0fe8998a9f.pdf)
