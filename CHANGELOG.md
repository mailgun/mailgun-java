# Mailgun Java SDK changelog

All _notable_ changes to this project will be documented in this file.

The format is based on _[Keep a Changelog][keepachangelog]_, and this project
adheres to _[Semantic Versioning][semver]_.

## [10.0.3] (released: 2025-07-24)
### Updated
- Add logs api.

## [2.0.0] (released: 2025-06-11)
### Updated
- Added unit tests and updated existing
- Moved to the new version 2.x.x as JDK was moved to Java 11

## [1.1.6] (released: 2025-04-28)
### Updated
- Fixed issues

## [1.1.5] (released: 2025-03-21)
### Updated
- Moved to Java 11

## [1.1.4] (released: 2025-002-20)
### Updated
- Added Metrics API

## [1.1.3] (released: 2024-03-01)
### Updated
- jar clean-up

## [1.1.2] (released: 2024-01-26)
### Updated
- add ability for MailgunMessagesApi to support arbitrary email headers through `.headers`.

## [1.1.1] (released: 2023-12-12)
### Updated
- add ability for primary accounts to make API calls on behalf of their subaccounts, e.g. sending messages, managing mailing lists, etc.

## [1.1.0] (released: 2023-10-23)
### Updated
- add ability to get the first / last / next / previous page data.

## [1.0.9] (released: 2023-09-27)
### Updated
- allows for appending custom MIME headers to a message

## [1.0.8] (released: 2023-05-25)
### Updated
- updated dependencies
- fixed generics in api creation

## [1.0.7] (released: 2023-01-11)
### Added
- default locale

## [1.0.6] (released: 2022-13-09)
### Replaced
- slf4j-simple with slf4j-api

## [1.0.5] (released: 2022-11-09)
### Added
- multiple FormData attachment support.

## [1.0.4] (released: 2022-10-10)
### Added
- FormData attachment support.
- default AsyncClient.

## [1.0.3] (released: 2022-09-23)
### Added
- attachments support.

## [1.0.2] (released: 2022-08-28)
### Added
- Non-English locale date support.
- JDK 17 support.

## [1.0.1] (released: 2022-06-20)

### Added
- Send email(s) in MIME format API
- Resend stored email  API
- Retrieve stored email(s) API
- Mailgun Async Client configuration
- Asynchronously send email(s) API
- Asynchronously send email(s) in MIME format API
- Asynchronously resend message API
- Bulk verification APIs
- Bulk verification previews APIs
- Seed List APIs
- Import a list of whitelists from CSV file API
- Import a list of unsubscribe from CSV file API
- Import a list of complaints from CSV file API
- Add Import a list of bounces from CSV file API

[2.0.0]: https://github.com/mailgun/mailgun-java/compare/v1.1.6...v2.0.0
[1.1.6]: https://github.com/mailgun/mailgun-java/compare/v1.1.5...v1.1.6
[1.1.5]: https://github.com/mailgun/mailgun-java/compare/v1.1.4...v1.1.5
[1.1.4]: https://github.com/mailgun/mailgun-java/compare/v1.1.3...v1.1.4
[1.1.3]: https://github.com/mailgun/mailgun-java/compare/release/1.1.2...release/1.1.3
[1.1.2]: https://github.com/mailgun/mailgun-java/compare/release/1.1.1...release/1.1.2
[1.1.1]: https://github.com/mailgun/mailgun-java/compare/release/1.1.0...release/1.1.1
[1.1.0]: https://github.com/mailgun/mailgun-java/compare/release/1.0.9...release/1.1.0
[1.0.9]: https://github.com/mailgun/mailgun-java/compare/release/1.0.8...release/1.0.9
[1.0.8]: https://github.com/mailgun/mailgun-java/compare/release/1.0.7...release/1.0.8
[1.0.7]: https://github.com/mailgun/mailgun-java/compare/release/1.0.6...release/1.0.7
[1.0.6]: https://github.com/mailgun/mailgun-java/compare/release/1.0.5...release/1.0.6
[1.0.5]: https://github.com/mailgun/mailgun-java/compare/release/1.0.4...release/1.0.5
[1.0.4]: https://github.com/mailgun/mailgun-java/compare/release/1.0.3...release/1.0.4
[1.0.3]: https://github.com/mailgun/mailgun-java/compare/release/1.0.2...release/1.0.3
[1.0.2]: https://github.com/mailgun/mailgun-java/compare/release/1.0.1...release/1.0.2
[1.0.1]: https://github.com/mailgun/mailgun-java/compare/release/1.0.0...release/1.0.1


[keepachangelog]: https://keepachangelog.com/
[semver]: https://semver.org/spec/v2.0.0.html