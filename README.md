# postfix-expression-tree-evaluator
## 1. Project Overview

This project is a RESTful Spring Boot application that parses, stores, reconstructs, and evaluates algebraic equations using a **Postfix (Reverse Polish Notation) Expression Tree**.

The application:

* Accepts algebraic expressions in infix format
* Converts them to postfix notation
* Builds a binary expression tree
* Stores the tree in memory
* Evaluates expressions using variable substitution
* Reconstructs infix expressions from the stored tree

The implementation focuses on correctness, clean architecture, and algorithmic clarity rather than persistence concerns.

---

## 2. Core Objectives

* Implement infix → postfix conversion (Shunting Yard algorithm)
* Construct a postfix expression tree
* Evaluate expressions using recursive traversal
* Provide REST APIs for storing and evaluating equations
* Use in-memory storage as required
* Ensure proper validation and error handling
* Maintain separation of concerns across layers

---

## 3. Technology Stack

* Java 17+
* Spring Boot 3.x
* Maven
* JUnit 5
* ConcurrentHashMap (in-memory repository)
* Jakarta Validation

---

## 4. Architecture

The application follows a clean layered structure:

```
Controller
    ↓
Service
    ↓
Parser Layer
    ├── Tokenizer
    ├── PostfixConverter (Shunting Yard)
    └── ExpressionTreeBuilder
    ↓
Expression Tree Model
    ├── ExpressionNode (abstract)
    ├── OperandNode
    └── OperatorNode
    ↓
In-Memory Repository (ConcurrentHashMap)
```

### Design Principles Applied

* Separation of Concerns
* Single Responsibility Principle
* Immutable Node Design
* Thread-safe storage
* Centralized exception handling
* REST-compliant API structure

---

## 5. Expression Handling Workflow

### 5.1 Store Equation

1. Input equation received in infix form
2. Tokenized into operands and operators
3. Converted to postfix notation
4. Postfix expression tree constructed
5. Tree stored in memory

### 5.2 Retrieve Equation

1. Traverse expression tree
2. Reconstruct infix representation
3. Return formatted response

### 5.3 Evaluate Equation

1. Fetch stored tree by ID
2. Substitute variable values
3. Recursively evaluate tree
4. Return computed result

---

## 6. Supported Features

### Operators

* Addition (+)
* Subtraction (-)
* Multiplication (*)
* Division (/)
* Exponentiation (^)

### Expression Support

* Parentheses
* Coefficient variables (e.g., 3x)
* Pure variables (x, y)
* Numeric constants
* Floating point values

---

## 7. API Endpoints

---

### 7.1 Store Equation

**POST**
`/api/equations/store`

#### Request

```json
{
  "equation": "3x + 2y - z"
}
```

#### Response

```json
{
  "message": "Equation stored successfully",
  "equationId": "e76aa57b-2681-432c-b01c-f649e263317f"
}
```

---

### 7.2 Retrieve Stored Equations

**GET**
`/api/equations`

#### Response

```json
{
  "equations": [
    {
      "equationId": "e76aa57b-2681-432c-b01c-f649e263317f",
      "equation": "((3x+2y)-z)"
    }
  ]
}
```

---

### 7.3 Evaluate Equation

**POST**
`/api/equations/{equationId}/evaluate`

#### Request

```json
{
  "variables": {
    "x": 2,
    "y": 3,
    "z": 1
  }
}
```

#### Response

```json
{
  "equationId": "e76aa57b-2681-432c-b01c-f649e263317f",
  "equation": "3x + 2y - z",
  "variables": {
    "x": 2,
    "y": 3,
    "z": 1
  },
  "result": 11
}
```

---

## 8. Error Handling

The application validates:

* Missing variables
* Division by zero
* Malformed expressions
* Invalid operators
* Missing equation ID
* Empty input

Errors are handled via centralized `@RestControllerAdvice`.

Example error response:

```json
{
  "error": "Missing variable: z"
}
```

---

## 9. Running the Application

### 9.1 Prerequisites

* Java 17+
* Maven

### 9.2 Start Application

```bash
mvn spring-boot:run
```

Application will run at:

```
http://localhost:8080
```

---

## 10. Running Tests

```bash
mvn test
```

Unit tests cover:

* Tokenization
* Postfix conversion
* Tree construction
* Expression evaluation
* Error scenarios

---

## 11. Performance Characteristics

| Operation         | Time Complexity |
| ----------------- | --------------- |
| Tokenization      | O(n)            |
| Infix to Postfix  | O(n)            |
| Tree Construction | O(n)            |
| Evaluation        | O(n)            |

Overall complexity per equation: **O(n)**

---

## 12. Storage Strategy

The repository uses:

```
ConcurrentHashMap<String, Equation>
```

Data is stored in-memory and resets on application restart, as required by the specification.

---

* Help you prepare how to explain this in an interview
* Or review your final project structure before submission

Tell me the next step.
