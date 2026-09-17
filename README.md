<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=waving&color=0:1f6feb,100:8250df&height=200&section=header&text=JDBC%20%E2%80%94%20Java%20Database%20Connectivity&fontSize=36&fontColor=ffffff&animation=fadeIn&fontAlignY=38&desc=Java%20%E2%86%92%20JDBC%20%E2%86%92%20MySQL&descAlignY=55&descSize=18" />
</p>

<p align="center">
  <img src="https://readme-typing-svg.demolab.com?font=Fira+Code&weight=600&size=22&duration=3000&pause=800&color=2F81F7&center=true&vCenter=true&width=650&lines=Practical+JDBC+concepts;Hands-on+database+operations;Java+%E2%86%92+JDBC+%E2%86%92+MySQL" />
</p>

<p align="center">
  <img src="https://img.shields.io/badge/JAVA-21-ED8B00?style=flat-square&logo=openjdk&logoColor=white">
  <img src="https://img.shields.io/badge/JDBC-API-2F81F7?style=flat-square">
  <img src="https://img.shields.io/badge/MYSQL-Database-4479A1?style=flat-square&logo=mysql&logoColor=white">
  <img src="https://img.shields.io/badge/HikariCP-Pooling-8250df?style=flat-square">
</p>

<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=rect&color=0:1f6feb,100:8250df&height=3&width=1000&animation=fadeIn" />
</p>

## 📚 Table of Contents

1. [Overview](#-jdbc)
2. [JDBC API](#-jdbc-api)
3. [JDBC Flow](#-jdbc-flow)
4. [SQL Operations](#️-sql-operations)
5. [ResultSet](#-resultset)
6. [PreparedStatement](#️-preparedstatement)
7. [CallableStatement](#-callablestatement)
8. [Batch Operations](#️-batch-operations)
9. [Transactions](#-transactions)
10. [Connection Pooling](#-connection-pooling)
11. [Database Configuration](#️-database-configuration)
12. [BLOB Handling](#️-blob)
13. [RowSet](#-rowset)
14. [Exception Handling](#-exception-handling)
15. [DatabaseMetaData](#️-databasemetadata)
16. [JDBC Drivers](#-jdbc-drivers)

<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=rect&color=0:1f6feb,100:8250df&height=3&width=1000&animation=fadeIn" />
</p>

## ⚡ JDBC

JDBC provides the API required for a Java application to communicate with a relational database. It sits between the application and the database driver, translating Java calls into database-specific operations.

```mermaid
flowchart LR
    JAVA["☕ Java Application"]
    JDBC["⚡ JDBC API"]
    DRIVER["🔌 JDBC Driver"]
    DB[("🗄️ MySQL")]

    JAVA --> JDBC --> DRIVER --> DB
    DB --> DRIVER --> JDBC --> JAVA
```

<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=rect&color=0:1f6feb,100:8250df&height=3&width=1000&animation=fadeIn" />
</p>

## 🧩 JDBC API

```mermaid
mindmap
  root((JDBC))
    Driver
    Connection
    Statement
    PreparedStatement
    CallableStatement
    ResultSet
    RowSet
    DriverManager
    DatabaseMetaData
    SQLException
```

| Component | Purpose |
|---|---|
| `DriverManager` | Locates and loads the correct JDBC driver, manages `Connection` creation |
| `Connection` | Represents a live session with the database |
| `Statement` | Executes static SQL with no parameters |
| `PreparedStatement` | Executes parameterized, precompiled SQL |
| `CallableStatement` | Executes stored procedures |
| `ResultSet` | Represents the tabular result of a query |
| `RowSet` | A connected/disconnected wrapper around `ResultSet` |
| `SQLException` | Thrown for any database access error |

<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=rect&color=0:1f6feb,100:8250df&height=3&width=1000&animation=fadeIn" />
</p>

## 🔄 JDBC Flow

```mermaid
flowchart LR
    A["01<br/>Load Driver"] -->
    B["02<br/>Connection"] -->
    C["03<br/>Statement"] -->
    D["04<br/>Execute"] -->
    E["05<br/>Result"] -->
    F["06<br/>Close"]

    style A fill:#1f6feb,color:#fff,stroke:none
    style B fill:#1f6feb,color:#fff,stroke:none
    style C fill:#1f6feb,color:#fff,stroke:none
    style D fill:#8250df,color:#fff,stroke:none
    style E fill:#238636,color:#fff,stroke:none
    style F fill:#6e7781,color:#fff,stroke:none
```

The driver registers itself automatically, a `Connection` is opened, a `Statement` is prepared and executed, results are read, and every resource is closed in reverse order.

<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=rect&color=0:1f6feb,100:8250df&height=3&width=1000&animation=fadeIn" />
</p>

## 🗃️ SQL Operations

```mermaid
flowchart TB
    SQL["SQL"]
    SQL --> SELECT["SELECT"]
    SQL --> NONSELECT["NON-SELECT"]

    SELECT --> EQ["executeQuery()"]
    NONSELECT --> EU["executeUpdate()"]

    EQ --> RS["ResultSet"]
    EU --> ROWS["Rows Affected"]

    style SQL fill:#161b22,color:#fff,stroke:#30363d
    style SELECT fill:#1f6feb,color:#fff,stroke:none
    style NONSELECT fill:#1f6feb,color:#fff,stroke:none
    style EQ fill:#238636,color:#fff,stroke:none
    style EU fill:#238636,color:#fff,stroke:none
```

| Method | Used For | Returns |
|---|---|---|
| `executeQuery()` | `SELECT` | `ResultSet` |
| `executeUpdate()` | `INSERT` / `UPDATE` / `DELETE` / DDL | `int` (rows affected) |
| `execute()` | SQL of unknown type | `boolean` |

<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=rect&color=0:1f6feb,100:8250df&height=3&width=1000&animation=fadeIn" />
</p>

## 📊 ResultSet

```text
Before First
     │
     ▼
 ┌───────┬────────┬────────┬────────┐
 │ Row 1 │ Row 2  │ Row 3  │ Row 4  │
 └───────┴────────┴────────┴────────┘
     ▲
     │
   Cursor
```

**Navigation:** `next()` · `previous()` · `first()` · `last()` · `absolute(n)`

| Type | Behavior |
|---|---|
| `TYPE_FORWARD_ONLY` | Cursor moves forward only |
| `TYPE_SCROLL_INSENSITIVE` | Scrollable, static snapshot |
| `TYPE_SCROLL_SENSITIVE` | Scrollable, reflects live changes |

| Concurrency | Behavior |
|---|---|
| `CONCUR_READ_ONLY` | Cannot update through the `ResultSet` |
| `CONCUR_UPDATABLE` | Can update through the `ResultSet` |

<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=rect&color=0:1f6feb,100:8250df&height=3&width=1000&animation=fadeIn" />
</p>

## 🛡️ PreparedStatement

Parameterized SQL using `?` placeholders, precompiled by the database for reuse and protection against SQL injection.

| | `Statement` | `PreparedStatement` |
|---|---|---|
| Compilation | Every execution | Once, reused |
| Parameters | String concatenation | Bound via `set*()` |
| Injection risk | High | Protected |

<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=rect&color=0:1f6feb,100:8250df&height=3&width=1000&animation=fadeIn" />
</p>

## 📞 CallableStatement

Used for executing stored procedures.

```mermaid
flowchart LR
    JAVA["Java"] --> CS["CallableStatement"]
    CS --> SP["Stored Procedure"]
    SP --> DB[("MySQL")]

    style JAVA fill:#161b22,color:#fff,stroke:#30363d
    style CS fill:#1f6feb,color:#fff,stroke:none
    style SP fill:#8250df,color:#fff,stroke:none
    style DB fill:#4479A1,color:#fff,stroke:none
```

**Parameter modes:** `IN` · `OUT` · `INOUT`

<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=rect&color=0:1f6feb,100:8250df&height=3&width=1000&animation=fadeIn" />
</p>

## ⚙️ Batch Operations

Multiple SQL operations grouped and executed together, reducing round trips to the database.

```mermaid
flowchart LR
    A["SQL 1"] --> B["Batch"]
    C["SQL 2"] --> B
    D["SQL 3"] --> B

    B --> E["executeBatch()"]
    E --> F[("Database")]

    style B fill:#8250df,color:#fff,stroke:none
    style E fill:#238636,color:#fff,stroke:none
    style F fill:#4479A1,color:#fff,stroke:none
```

<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=rect&color=0:1f6feb,100:8250df&height=3&width=1000&animation=fadeIn" />
</p>

## 🔐 Transactions

```mermaid
flowchart LR
    START["Transaction"] --> O1["Operation 1"]
    O1 --> O2["Operation 2"]
    O2 --> CHECK{"Success?"}

    CHECK -->|Yes| COMMIT["COMMIT"]
    CHECK -->|No| ROLLBACK["ROLLBACK"]

    style START fill:#161b22,color:#fff,stroke:#30363d
    style O1 fill:#1f6feb,color:#fff,stroke:none
    style O2 fill:#1f6feb,color:#fff,stroke:none
    style COMMIT fill:#238636,color:#fff,stroke:none
    style ROLLBACK fill:#da3633,color:#fff,stroke:none
```

**ACID:** Atomicity · Consistency · Isolation · Durability

**Savepoints** let a rollback undo part of a transaction without discarding the whole thing.

**Isolation levels:** `READ_UNCOMMITTED` · `READ_COMMITTED` · `REPEATABLE_READ` · `SERIALIZABLE`

<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=rect&color=0:1f6feb,100:8250df&height=3&width=1000&animation=fadeIn" />
</p>

## 🏊 Connection Pooling

Keeps reusable database connections warm and ready, avoiding the overhead of opening a new connection per request.

```mermaid
flowchart TB
    APP["Java Application"]

    APP --> POOL["Connection Pool"]

    POOL --> C1["Connection"]
    POOL --> C2["Connection"]
    POOL --> C3["Connection"]
    POOL --> CN["..."]

    C1 --> DB[("MySQL")]
    C2 --> DB
    C3 --> DB
    CN --> DB

    style APP fill:#161b22,color:#fff,stroke:#30363d
    style POOL fill:#8250df,color:#fff,stroke:none
    style C1 fill:#1f6feb,color:#fff,stroke:none
    style C2 fill:#1f6feb,color:#fff,stroke:none
    style C3 fill:#1f6feb,color:#fff,stroke:none
    style CN fill:#1f6feb,color:#fff,stroke:none
    style DB fill:#4479A1,color:#fff,stroke:none
```

**HikariCP** — a lightweight, high-performance pool that manages a fixed set of connections.

<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=rect&color=0:1f6feb,100:8250df&height=3&width=1000&animation=fadeIn" />
</p>

## ⚙️ Database Configuration

Database properties live in a separate configuration file, keeping credentials out of source code.

```mermaid
flowchart LR
    P["DB.properties"] --> PROP["Properties"]
    PROP --> CONFIG["HikariConfig"]
    CONFIG --> POOL["Connection Pool"]
```

> ⚠️ Keep credentials out of version control.

<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=rect&color=0:1f6feb,100:8250df&height=3&width=1000&animation=fadeIn" />
</p>

## 🖼️ BLOB

Storing and retrieving binary data (e.g. images) using database `BLOB` columns.

```mermaid
flowchart LR
    FILE["Image File"] --> INPUT["FileInputStream"]
    INPUT --> JDBC["PreparedStatement"]
    JDBC --> BLOB["BLOB"]
    BLOB --> DB[("Database")]

    DB --> RS["ResultSet"]
    RS --> OUTPUT["OutputStream"]
    OUTPUT --> IMAGE["Image File"]
```

<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=rect&color=0:1f6feb,100:8250df&height=3&width=1000&animation=fadeIn" />
</p>

## 📦 RowSet

`JdbcRowSet` wraps a `ResultSet` as a JavaBean, supporting both connected and disconnected use.

<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=rect&color=0:1f6feb,100:8250df&height=3&width=1000&animation=fadeIn" />
</p>

## ⚠️ Exception Handling

Database operations throw a checked `SQLException`, carrying an error code, `SQLState`, and — for batch operations — a chain of underlying exceptions. `try-with-resources` ensures everything closes even on failure.

<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=rect&color=0:1f6feb,100:8250df&height=3&width=1000&animation=fadeIn" />
</p>

## 🗂️ DatabaseMetaData

Introspects the database itself — driver name and version, supported features, and the schema's tables and columns.

<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=rect&color=0:1f6feb,100:8250df&height=3&width=1000&animation=fadeIn" />
</p>

## 🚗 JDBC Drivers

```mermaid
flowchart LR
    JDBC["JDBC Drivers"]

    JDBC --> T1["Type 1"]
    JDBC --> T2["Type 2"]
    JDBC --> T3["Type 3"]
    JDBC --> T4["Type 4"]

    T1 --> OLD["Outdated"]
    T2 --> OLD
    T3 --> OLD
    T4 --> USED["Used"]

    style JDBC fill:#161b22,color:#fff,stroke:#30363d
    style T1 fill:#6e7781,color:#fff,stroke:none
    style T2 fill:#6e7781,color:#fff,stroke:none
    style T3 fill:#6e7781,color:#fff,stroke:none
    style T4 fill:#1f6feb,color:#fff,stroke:none
    style OLD fill:#6e7781,color:#fff,stroke:none
    style USED fill:#238636,color:#fff,stroke:none
```

| Type | Name | Notes |
|---|---|---|
| 1 | JDBC-ODBC Bridge | Outdated |
| 2 | Native-API | Platform-dependent |
| 3 | Network Protocol | Rarely used |
| 4 | Thin Driver | Pure Java — used here |

<p align="center">
  <img src="https://readme-typing-svg.demolab.com?font=Fira+Code&weight=600&size=20&duration=2500&pause=1000&color=238636&center=true&vCenter=true&width=500&lines=Learning+through+implementation." />
</p>

<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=waving&color=0:8250df,100:1f6feb&height=120&section=footer&animation=fadeIn" />
</p>
