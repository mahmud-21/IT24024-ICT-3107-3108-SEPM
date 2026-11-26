# Software Engineering – Software Design
## Name : Md Mahmudur Rahman ID : IT24024 
## OBE-Based Question Paper (Bloom's Taxonomy, BNQF-Aligned)

| Item | Detail |
| --- | --- |
| **Course** | Software Engineering |
| **Topic** | Software Design (design fundamentals, metrics, architecture, design patterns) |
| **Source** | Lecture slides: `4-SoftEng-Design.ppt` |
| **Total Marks** | 40 |
| **Time** | 1 Hour |
| **Domain** | Cognitive |
| **Bloom Levels** | C1 Knowledge, C2 Understanding |
| **Framework** | OBE, BNQF-aligned (Bachelor level, Level 7) |

---

## 1. Course Outcomes (CO)

| CO | Course Outcome | Bloom Level |
| --- | --- | --- |
| CO1 | Explain the software design process, its steps and its outcomes. | C1–C2 |
| CO2 | Explain the principles of good design: modularity, coupling, cohesion, abstraction, encapsulation and information hiding. | C1–C2 |
| CO3 | Describe and interpret design quality measures (design reviews and CK metrics: CBO, LCOM, WMC). | C1–C2 |
| CO4 | Describe software architecture and common architectural styles. | C1–C2 |
| CO5 | Explain design patterns (GoF and Enterprise Application Architecture patterns), with their benefits and liabilities. | C1–C2 |

---

## 2. Question Paper

**Instructions:** Answer **all** questions. Marks are shown in brackets.

### Question 1 (8 Marks) – CO1
**a)** Define software design. State the input of the design process. **[3 | C1 Knowledge]**

**b)** Distinguish architectural design from detailed design. Explain why recording the rationale of design decisions improves long-term maintainability. **[5 | C2 Understanding]**

### Question 2 (8 Marks) – CO2
**a)** Define *coupling* and *cohesion*. **[3 | C1 Knowledge]**

**b)** Explain why a good design aims for low coupling and high cohesion. Describe how abstraction, encapsulation and information hiding support modularity. **[5 | C2 Understanding]**

### Question 3 (8 Marks) – CO3
**a)** State the formulas for CBO and LCOM2, and name what the "W" in WMC stands for. **[3 | C1 Knowledge]**

**b)** A `Rectangle` class has 4 attributes (x, y, width, height) and 3 methods (constructor, `draw()`, `calculateArea()`). The attribute usage counts are m(x) = 2, m(y) = 2, m(width) = 3, m(height) = 3. Compute LCOM2 and interpret the result. Explain what LCOM2 = 0 and LCOM2 = 1 would indicate. **[5 | C2 Understanding]**

### Question 4 (8 Marks) – CO4
**a)** Define software architecture. List four architectural styles. **[3 | C1 Knowledge]**

**b)** Explain the *Layered* style and the *Model–View–Controller (MVC)* style. Describe the responsibility of each layer or role, and give one situation where each is suitable. **[5 | C2 Understanding]**

### Question 5 (8 Marks) – CO5
**a)** Define a design pattern. Name the three GoF pattern categories and give one example pattern for each. **[3 | C1 Knowledge]**

**b)** Explain the *Strategy* pattern: its intent, the roles of Strategy, ConcreteStrategy and Context (use the Navigator/RouteStrategy example), and one benefit and one liability. **[5 | C2 Understanding]**

---

## 3. Question–CO–Bloom Mapping

| Question | Marks | Slide Topic | CO | Bloom Level | Cognitive Verb |
| --- | --- | --- | --- | --- | --- |
| Q1(a) | 3 | What is software design? | CO1 | C1 Knowledge | Define, State |
| Q1(b) | 5 | Design steps and outcome | CO1 | C2 Understanding | Distinguish, Explain |
| Q2(a) | 3 | Coupling and cohesion | CO2 | C1 Knowledge | Define |
| Q2(b) | 5 | Modularity, abstraction, information hiding | CO2 | C2 Understanding | Explain, Describe |
| Q3(a) | 3 | CK metrics (CBO, LCOM2, WMC) | CO3 | C1 Knowledge | State |
| Q3(b) | 5 | LCOM2 and the Rectangle example | CO3 | C2 Understanding | Compute, Interpret, Explain |
| Q4(a) | 3 | Software architecture and styles | CO4 | C1 Knowledge | Define, List |
| Q4(b) | 5 | Layered and MVC styles | CO4 | C2 Understanding | Explain, Describe |
| Q5(a) | 3 | Design patterns and GoF classification | CO5 | C1 Knowledge | Define, Name |
| Q5(b) | 5 | Strategy pattern | CO5 | C2 Understanding | Explain |

---

## 4. Assessment Blueprint

| CO | C1 Knowledge | C2 Understanding | Total |
| --- | --- | --- | --- |
| CO1 | 3 | 5 | 8 |
| CO2 | 3 | 5 | 8 |
| CO3 | 3 | 5 | 8 |
| CO4 | 3 | 5 | 8 |
| CO5 | 3 | 5 | 8 |
| **Total** | **15 (37.5%)** | **25 (62.5%)** | **40** |

---

## 5. Marking Guide (for examiners)

- **Q1(a):** Design is a problem-solving process (1.5). The input is the requirements (1.5).
- **Q1(b):** Architectural design organises software into components (2). Detailed design describes component behaviour (1.5). Rationale helps future maintainers (1.5).
- **Q2(a):** Coupling is interdependence among components (1.5). Cohesion is the strength of association within a component (1.5).
- **Q2(b):** Low coupling keeps changes local (1.5). High cohesion gives one clear responsibility (1.5). Abstraction (0.75). Encapsulation and information hiding (0.75). Link to modularity and interfaces (1).
- **Q3(a):** CBO(A) = number of classes used by A (1). LCOM2 = 1 − Σm(ai) / (m·a) (1.5). W = weighted by method complexity (0.5).
- **Q3(b):** Σm(ai) = 2+2+3+3 = 10 (1). LCOM2 = 1 − 10/12 = 1/6 ≈ 0.166 (2). Close to 0, so highly cohesive (1). 0 means every method uses every attribute; 1 means none does (1).
- **Q4(a):** Definition: structures comprising elements, relations and properties (2). Any four styles: layered, event-based, MVC, blackboard/repository, pipes and filters (1).
- **Q4(b):** Layered: each layer depends only on the next (2.5). MVC: model, view, controller roles (2.5).
- **Q5(a):** Pattern = recurring problem and core solution (1). Creational, Structural, Behavioral (1). One example each (1).
- **Q5(b):** Intent (1.5). Roles: RouteStrategy, WalkingStrategy/CarStrategy, Navigator (2). One benefit (1). One liability (0.5).

---

## 6. Notes

- COs are derived from the slide content; replace with the department's official CO statements if they differ.
- Confirm the BNQF level and descriptors against your department's mapping.
