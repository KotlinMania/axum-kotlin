# axum-kotlin in Kotlin

[![GitHub link](https://img.shields.io/badge/GitHub-KotlinMania%2Faxum--kotlin-blue.svg)](https://github.com/KotlinMania/axum-kotlin)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.kotlinmania/axum-kotlin)](https://central.sonatype.com/artifact/io.github.kotlinmania/axum-kotlin)
[![Build status](https://img.shields.io/github/actions/workflow/status/KotlinMania/axum-kotlin/ci.yml?branch=main)](https://github.com/KotlinMania/axum-kotlin/actions)

This is a Kotlin Multiplatform line-by-line transliteration port of [`tokio-rs/axum`](https://github.com/tokio-rs/axum).

**Original Project:** This port is based on [`tokio-rs/axum`](https://github.com/tokio-rs/axum). All design credit and project intent belong to the upstream authors; this repository is a faithful port to Kotlin Multiplatform with no behavioural changes intended.

### Porting status

This is an **in-progress port**. The goal is feature parity with the upstream Rust crate while providing a native Kotlin Multiplatform API. Every Kotlin file carries a `// port-lint: source <path>` header naming its upstream Rust counterpart so the AST-distance tool can track provenance.

---

## Upstream README — `tokio-rs/axum`

> The text below is reproduced and lightly edited from [`https://github.com/tokio-rs/axum`](https://github.com/tokio-rs/axum). It is the upstream project's own description and remains under the upstream authors' authorship; links have been rewritten to absolute upstream URLs so they continue to resolve from this repository.

## axum

`axum` is an HTTP routing and request-handling library that focuses on ergonomics and modularity.

[![Build status](https://github.com/tokio-rs/axum/actions/workflows/CI.yml/badge.svg?branch=main)](https://github.com/tokio-rs/axum/actions/workflows/CI.yml)
[![Crates.io](https://img.shields.io/crates/v/axum)](https://crates.io/crates/axum)
[![Documentation](https://docs.rs/axum/badge.svg)][docs]

More information about this crate can be found in the [crate documentation][docs].

## High level features

- Route requests to handlers with a macro free API.
- Declaratively parse requests using extractors.
- Simple and predictable error handling model.
- Generate responses with minimal boilerplate.
- Take full advantage of the [`tower`] and [`tower-http`] ecosystem of
  middleware, services, and utilities.

In particular the last point is what sets `axum` apart from other libraries / frameworks.
`axum` doesn't have its own middleware system but instead uses
[`tower::Service`]. This means `axum` gets timeouts, tracing, compression,
authorization, and more, for free. It also enables you to share middleware with
applications written using [`hyper`] or [`tonic`].

## ⚠ Breaking changes ⚠

We are currently working towards axum 0.9 so the `main` branch contains breaking
changes. See the [`0.8.x`] branch for what's released to crates.io.

[`0.8.x`]: https://github.com/tokio-rs/axum/tree/v0.8.x

## Usage example

```rust
use axum::{
    routing::{get, post},
    http::StatusCode,
    Json, Router,
};
use serde::{Deserialize, Serialize};

#[tokio::main]
async fn main() {
    // initialize tracing
    tracing_subscriber::fmt::init();

    // build our application with a route
    let app = Router::new()
        // `GET /` goes to `root`
        .route("/", get(root))
        // `POST /users` goes to `create_user`
        .route("/users", post(create_user));

    // run our app with hyper, listening globally on port 3000
    let listener = tokio::net::TcpListener::bind("0.0.0.0:3000").await.unwrap();
    axum::serve(listener, app).await;
}

// basic handler that responds with a static string
async fn root() -> &'static str {
    "Hello, World!"
}

async fn create_user(
    // this argument tells axum to parse the request body
    // as JSON into a `CreateUser` type
    Json(payload): Json<CreateUser>,
) -> (StatusCode, Json<User>) {
    // insert your application logic here
    let user = User {
        id: 1337,
        username: payload.username,
    };

    // this will be converted into a JSON response
    // with a status code of `201 Created`
    (StatusCode::CREATED, Json(user))
}

// the input to our `create_user` handler
#[derive(Deserialize)]
struct CreateUser {
    username: String,
}

// the output to our `create_user` handler
#[derive(Serialize)]
struct User {
    id: u64,
    username: String,
}
```

You can find this [example][readme-example] as well as other example projects in
the [example directory][examples].

See the [crate documentation][docs] for way more examples.

## Performance

`axum` is a relatively thin layer on top of [`hyper`] and adds very little
overhead. So `axum`'s performance is comparable to [`hyper`]. You can find
benchmarks [here](https://github.com/programatik29/rust-web-benchmarks) and
[here](https://web-frameworks-benchmark.netlify.app/result?l=rust).

## Safety

This crate uses `#![forbid(unsafe_code)]` to ensure everything is implemented in
100% safe Rust.

## Minimum supported Rust version

axum's MSRV is 1.80.

## Examples

The [examples] folder contains various examples of how to use `axum`. The
[docs] also provide lots of code snippets and examples.

## Getting Help

In the `axum`'s repo we also have a [number of examples][examples] showing how
to put everything together. You're also welcome to ask in the [Discord channel][chat] or open a [discussion] with your question.

## Contributing

🎈 Thanks for your help improving the project! We are so happy to have
you! We have a [contributing guide][contributing] to help you get involved in the
`axum` project.

## License

This project is licensed under the [MIT license][license].

### Contribution

Unless you explicitly state otherwise, any contribution intentionally submitted
for inclusion in `axum` by you, shall be licensed as MIT, without any
additional terms or conditions.

[readme-example]: https://github.com/tokio-rs/axum/tree/main/examples/readme
[examples]: https://github.com/tokio-rs/axum/tree/main/examples
[docs]: https://docs.rs/axum
[`tower`]: https://crates.io/crates/tower
[`hyper`]: https://crates.io/crates/hyper
[`tower-http`]: https://crates.io/crates/tower-http
[`tonic`]: https://crates.io/crates/tonic
[contributing]: https://github.com/tokio-rs/axum/blob/main/CONTRIBUTING.md
[chat]: https://discord.gg/tokio
[discussion]: https://github.com/tokio-rs/axum/discussions/new?category=q-a
[`tower::Service`]: https://docs.rs/tower/latest/tower/trait.Service.html
[license]: https://github.com/tokio-rs/axum/blob/main/axum/LICENSE

---

## About this Kotlin port

### Installation

```kotlin
dependencies {
    implementation("io.github.kotlinmania:axum-kotlin:0.1.0-SNAPSHOT")
}
```

### Building

```bash
./gradlew build
./gradlew test
```

### Targets

- macOS arm64
- Linux x64
- Windows mingw-x64
- iOS arm64 / simulator-arm64 (Swift export + XCFramework)
- JS (browser + Node.js)
- Wasm-JS (browser + Node.js)
- Android (API 24+)

### Porting guidelines

See [AGENTS.md](AGENTS.md) and [CLAUDE.md](CLAUDE.md) for translator discipline, port-lint header convention, and Rust → Kotlin idiom mapping.

### License

This Kotlin port is distributed under the same MIT license as the upstream [`tokio-rs/axum`](https://github.com/tokio-rs/axum). See [LICENSE](LICENSE) (and any sibling `LICENSE-*` / `NOTICE` files mirrored from upstream) for the full text.

Original work copyrighted by the axum authors.  
Kotlin port: Copyright (c) 2026 Sydney Renee and The Solace Project.

### Acknowledgments

Thanks to the [`tokio-rs/axum`](https://github.com/tokio-rs/axum) maintainers and contributors for the original Rust implementation. This port reproduces their work in Kotlin Multiplatform; bug reports about upstream design or behavior should go to the upstream repository.
