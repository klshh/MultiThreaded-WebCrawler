# 🌐 Multithreaded Web Crawler

A **high-performance concurrent web crawler** written in Java using core concurrency primitives. It crawls all pages starting from a given URL, constrained to the same hostname. It uses a fixed thread pool and avoids revisiting pages using a thread-safe map.

---

## 📦 Features

- 🔁 **Multithreaded crawling** using `ExecutorService`
- 🧠 Strategy Pattern to inject your own HTML parser
- 🛡️ Thread-safe visited tracking using `ConcurrentHashMap`
- 🌍 Hostname filtering to stay within the same domain
- ✅ Waits for all tasks to complete before shutdown

---

## 🧠 Architecture Overview

```mermaid
flowchart TD
    A[Start URL] --> B[Submit Task to ExecutorService]
    B --> C{Is Hostname Valid}
    C -- No --> D[Skip URL]
    C -- Yes --> E[Add to Visited Map]
    E --> F[Increment Counter]
    F --> G[Submit New Task]
    G --> H[Extract URLs in run]
    H --> I{Is Hostname Same}
    I -- No --> J[Ignore URL]
    I -- Yes --> K{Visited Before}
    K -- Yes --> L[Skip URL]
    K -- No --> M[Mark Visited and Submit Task]
    M --> N[Increment Counter]
    N --> H
    H --> O[Decrement Counter]
    O --> P{Counter is 0}
    P -- Yes --> Q[Shutdown ExecutorService]
