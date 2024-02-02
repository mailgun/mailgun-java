[authentication]: https://documentation.mailgun.com/en/latest/api-intro.html#authentication-1
[api_keys]: https://app.mailgun.com/app/account/security/api_keys
[mailgun_home]: https://mailgun.com
[api_reference]: https://documentation.mailgun.com/en/latest/api_reference.html
[base_url_documentation]: https://documentation.mailgun.com/en/latest/api-intro.html#base-url-1
[changes-file]: ./CHANGELOG.md


<img width="300" src="https://images.ctfassets.net/y6oq7udscnj8/6ZBhy3wCQx3WhSaoejr2fs/d5ff47541079b886877a80ab5ca0a471/01_1121_SinchMailgunLogo_Mailgun-Colour.png" >

# Mailgun Java SDK 

[![Build Status](https://app.travis-ci.com/mailgun/mailgun-java.svg?branch=main)](https://app.travis-ci.com/github/mailgun/mailgun-java)
[![maven central](https://img.shields.io/maven-central/v/com.mailgun/mailgun-java.svg?label=maven%20central)](https://search.maven.org/search?q=g:%22com.mailgun%22%20AND%20a:%22mailgun-java%22)
[![License](http://img.shields.io/:license-apache-brightgreen.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)


[//]: # (* [Issues][sdk-issues])

[//]: # (* [Getting Help]&#40;#getting-help&#41;)


The **Mailgun Java SDK** enables Java developers to work with [Mailgun API][api_reference] efficiently.


* [Mailgun Home][mailgun_home]
* [Mailgun API documentation][api_reference]
* [Mailgun authentication documentation][authentication]
* [Base URL Documentation][base_url_documentation]



## Table of contents

- [Release notes](#release-notes)
- [Getting Started](#getting-started)
  - [Requirements](#requirements)
  - [Install the SDK](#install-the-sdk)
  - [Authentication](#authentication)
  - [Base URL](#base-url)
  - [Domains](#domains)
- [Setup Client](#setup-client)
  - [Client Configuration](#client-configuration)
- [Responses](#responses)
- [Exception handling](#Exception-handling)

- [Methods](#methods)

- [Testing](#testing)
- [Contribute](#contribute)

## Release notes

Changes to the SDK beginning with version 1.0.1 (June 2022) are tracked in [CHANGELOG.md][changes-file].

## Getting Started

### Requirements

To run the SDK, you will need **Java 1.8+**.


### Install the SDK

The recommended way to use the **Mailgun Java SDK** in your project: 

[Choose version you need](https://search.maven.org/search?q=g:%22com.mailgun%22%20AND%20a:%22mailgun-java%22)

Maven.

Add the following to your `pom.xml`:

```xml
<dependencies>
  ...
  <dependency>
    <groupId>com.mailgun</groupId>
    <artifactId>mailgun-java</artifactId>
    <version>1.1.2</version>
  </dependency>
  ...
</dependencies>
```

Gradle Groovy DSL .

```xml
implementation 'com.mailgun:mailgun-java:1.1.2'
```



### Authentication

When you [Sign up](https://signup.mailgun.com/new/signup), Mailgun generates a primary account API key. 

To view your primary account API key in the Mailgun dashboard, click on Settings on the left-hand nav in the Mailgun dashboard and then API Keys and click on the eye icon next to [API_KEYS][api_keys].



### Base URL


Mailgun allows sending and receiving an email in either our US or our EU regions.

Be sure to use the appropriate [Base URL][base_url_documentation] based on which region you've created your domain in.

For domains created in our **US region**, the base URL (**DEFAULT_BASE_URL**) is:
```
https://api.mailgun.net/
```
For domains created in our **EU region**, the base URL (**EU_BASE_URL**) is:
```
https://api.eu.mailgun.net/
```

### Domains

Your Mailgun account may contain multiple sending [domains](https://app.mailgun.com/app/sending/domains).

Most API URLs must include the name of the domain you're interested in.

For example:
```
mailgunMessagesApi.sendMessage(YOUR_DOMAIN, message);
```


## Setup Client

### Client Configuration

[Configuration examples](https://github.com/mailgun/mailgun-java/blob/main/src/test/java/com/mailgun/client/MailgunClientTest.java)

#### Default Mailgun Client configuration:

```java
//        For US servers
        MailgunClient.config(PRIVATE_API_KEY)
                
//        For EU servers        
        MailgunClient.config(EU_BASE_URL, PRIVATE_API_KEY)
```

#### Custom Mailgun client configuration.

You can specify your own logLevel, retryer, logger, errorDecoder, options.
```java
        MailgunClient.config(PRIVATE_API_KEY)
                .logLevel(Logger.Level.NONE)
                .retryer(new Retryer.Default())
                .logger(new Logger.NoOpLogger())
                .errorDecoder(new ErrorDecoder.Default())
                .options(new Request.Options(10, TimeUnit.SECONDS, 60, TimeUnit.SECONDS, true))
```

#### Mailgun client configuration with request interceptor for all API calls

You can add your multiple custom:

1) request header in format: ```(headerName, headerValue)```
2) form property with allowed prefixes such as: ```t:, o:, h:, v:``` with the followed by any arbitrary value.
```java
        MailgunMessagesApi mailgunMessagesApi = MailgunClient.config(API_KEY)
            .createApiWithRequestInterceptor(MailgunMessagesApi.class,
                MailgunRequestInterceptor.builder()
                    .addHeader(HEADER_ON_BEHALF_OF, SUBACCOUNT_ACCOUNT_ID)
                    .addProperty("h:X-My-Header", "my_custom_header")
                    .build()
            );
```

#### Mailgun client configuration example for the [Mailgun sending emails API](https://documentation.mailgun.com/en/latest/api-sending.html)

```java
        MailgunMessagesApi mailgunMessagesApi = MailgunClient.config(PRIVATE_API_KEY)
            .createApi(MailgunMessagesApi.class);
```


#### Async Mailgun client configuration example for the [Mailgun sending emails API](https://documentation.mailgun.com/en/latest/api-sending.html)

```java
        MailgunMessagesApi mailgunAsyncMessagesApi = MailgunClient.config(PRIVATE_API_KEY)
            .createAsyncApi(MailgunMessagesApi.class);
```

#### Spring Bean Mailgun client configuration example:
```java
        @Bean
        public MailgunMessagesApi mailgunMessagesApi() {
            return MailgunClient.config(PRIVATE_API_KEY)
                      .createApi(MailgunMessagesApi.class);
        }
```

_Hint: register Mailgun API Client as a **Singleton** and reuse it while sending emails to reduce resource consumption_


## Responses

Each method that returns a specific JavaBean class is duplicated with the method with suffix ending with `FeignResponse` returns a [Feign response](https://github.com/OpenFeign/feign/blob/master/core/src/main/java/feign/Response.java).

You can use methods that return a specific JavaBean class:
```java
        MessageResponse messageResponse = mailgunMessagesApi.sendMessage(YOUR_DOMAIN, message);
```

Or `FeignResponse`:
```java
        Response feignResponse = mailgunMessagesApi.sendMessageFeignResponse(YOUR_DOMAIN, message);
```

From `FeignResponse` you can get:
```java
//        status code
        int statusCode = feignResponse.status();
//        Headers
        Map<String, Collection<String>> headers = feignResponse.headers();
//        Protocol version
        Request.ProtocolVersion protocolVersion = feignResponse.protocolVersion();
//        etc.
```

More information:

[Feign HTTP Response](https://github.com/OpenFeign/feign/blob/master/core/src/main/java/feign/Response.java)

[Feign Java client](https://github.com/OpenFeign/feign)


But `Feign` does not have the functionality to deserialize responses out of the box.

To retrieves a JavaBean class from the `FeignResponse` you can use [decode](https://github.com/mailgun/mailgun-java/blob/main/src/main/java/com/mailgun/util/ObjectMapperUtil.java#L34) method:
```java
        MessageResponse messageResponse = ObjectMapperUtil.decode(feignResponse, MessageResponse.class);
```
Or
```java
        JsonNode jsonNode = ObjectMapperUtil.decode(feignResponse, JsonNode.class);
```

## Exception handling

[FeignException](https://github.com/OpenFeign/feign/blob/master/core/src/main/java/feign/FeignException.java) - origin exception type for all HTTP Apis.

From `FeignException` you can get:
```java
        try {
            MessageResponse messageResponse = mailgunMessagesApi.sendMessage(YOUR_DOMAIN, message);
        } catch (FeignException exception) {
//          Exception message
            String exceptionMessage = exception.getMessage();
//          status code                
            int statusCode = exception.status();
//          Headers
            Map<String, Collection<String>> headers = exception.headers();
//          etc.
```
More information:
[FeignException](https://github.com/OpenFeign/feign/blob/master/core/src/main/java/feign/FeignException.java)



## Methods

## Request examples

- [Methods](#methods)
  - [Messages](#messages)
    - [Set up MailgunMessagesApi](#Set-up-MailgunMessagesApi)
    - [Send email](#send-email)
    - [Send email(name with email)](#send-email-name-with-email)
    - [Send emails](#send-emails)
    - [Async send email(s)](#async-send-emails)
    - [Send email (html example)](#send-email-html-example)
    - [Send email (attachments example)](#send-email-attachments-example)
    - [Send email (attachment FormData example)](#send-email-attachment-FormData-example)
    - [Send email (inline multiple files example)](#send-email-inline-multiple-files-example)
    - [Send email (delay example)](#send-email-delay-example)
    - [Send email (reply-to example)](#send-email-reply-to-example)
    - [Send email (mailing list example)](#send-email-mailing-list-example)
    - [Send email (sender example)](#send-email-sender-example)
    - [Send email (with custom form property)](#send-email-with-custom-form-property)
    - [Send email(s) in MIME format](#send-mime-emails)
  - [Store Messages](#store-messages)
    - [Set up MailgunStoreMessagesApi](#Set-up-MailgunStoreMessagesApi)
    - [Resend email(s)](#resend-emails)
    - [Retrieve email](#retrieve-email)
  - [Domains](#domains)
    - [Set up MailgunDomainsApi](#Set-up-MailgunDomainsApi)
    - [Get Domains list](#Get-Domains-list)
    - [Get single Domains](#Get-single-Domains)
    - [Verify Domain](#Verify-Domain)
    - [Create Domain](#Create-Domain)
    - [Delete Domain](#Delete-Domain)
    - [Create credentials](#Create-credentials)
    - [Update credentials](#Update-credentials)
    - [Get connection settings](#Get-connection-settings)
    - [Update connection settings](#Update-connection-settings)
    - [Get tracking settings](#Get-tracking-settings)
    - [Update tracking settings](#Update-tracking-settings)
    - [Update click tracking settings](#Update-click-tracking-settings)
    - [Update unsubscribe tracking settings](#Update-click-tracking-settings)
  - [IPs](#IPs)
    - [Set up MailgunIPsApi](#set-up-MailgunIPsApi)
    - [Get all IPs](#Get-all-IPs)
    - [Get dedicated IPs](#Get-dedicated-IPs)
    - [Get specified IP](#Get-specified-IP)
    - [Get domain IP](#Get-domain-IP)
    - [Assign IP to domain](#Assign-IP-to-domain)
    - [Unassign IP from domain](#Unassign-IP-from-domain)
  - [Events](#Events)
    - [Set up MailgunEventsApi](#Set-up-MailgunEventsApi)
    - [Get all Events](#Get-all-Events)
    - [Get specified Events](#Get-specified-Events)
    - [Get Events next page](#Get-Events-next-page)
  - [Stats](#Stats)
    - [Set up MailgunStatisticsApi](#Set-up-MailgunStatisticsApi)
    - [Get domain Statistics](#Get-domain-Statistics)
  - [Tags](#Tags)
    - [Set up MailgunTagsApi](#Set-up-MailgunTagsApi)
    - [Get all Tags](#Get-all-Tags)
    - [Get Tag](#Get-Tag)
    - [Update Tag](#Update-Tag)
    - [Get Tag statistics](#Get-Tag-statistics)
    - [Delete Tag](#Delete-Tag)
    - [List Tag countries](#List-Tag-countries)
    - [List Tag providers](#List-Tag-providers)
    - [List Tag devices](#List-Tag-devices)
  - [Suppressions](#Suppressions)
    - [Suppression Bounces](#Suppression-Bounces)
      - [Set up MailgunSuppressionBouncesApi](#Set-up-MailgunSuppressionBouncesApi)
      - [Get Bounces](#Get-Bounces)
      - [Get Bounce](#Get-Bounce)
      - [Add Bounce](#Add-Bounce)
      - [Add Bounces](#Add-Bounces)
      - [Import Bounces](#Import-Bounce)
      - [Delete Bounce](#Delete-Bounce)
      - [Delete all Bounces](#Delete-all-Bounces)
    - [Suppression Complaints](#Suppression-Complaints)
      - [Set up MailgunSuppressionComplaintsApi](#Set-up-MailgunSuppressionComplaintsApi)
      - [Get all Complaints](#Get-all-Complaints)
      - [Get single Complaint](#Get-single-Complaint)
      - [Add Address](#Add-Address)
      - [Add Addresses](#Add-Addresses)
      - [Import Addresses](#Import-Addresses)
      - [Remove Address](#Remove-Address)
    - [Suppression Unsubscribe](#Suppression-Unsubscribe)
      - [Set up MailgunSuppressionUnsubscribeApi](#Set-up-MailgunSuppressionUnsubscribeApi)
      - [Get all Unsubscribe](#Get-all-Unsubscribe)
      - [Get single Unsubscribe](#Get-single-Unsubscribe)
      - [Add address](#Add-address)
      - [Add addresses](#Add-addresses)
      - [Import addresses](#Import-addresses)
      - [Remove address tag](#Remove-address-tag)
      - [Remove address](#Remove-address)
    - [Suppression Whitelists](#Suppression-Whitelists)
      - [Set up MailgunSuppressionWhitelistsApi](#Set-up-MailgunSuppressionWhitelistsApi)
      - [Get all Whitelists](#Get-all-Whitelists)
      - [Get single Whitelist](#Get-single-Whitelist)
      - [Add address/domain](#Add-address/domain)
      - [Import address/domain](#Import-address/domain)
      - [Delete address/domain](#Delete-address/domain)
  - [Routes](#Routes)
    - [Set up MailgunRoutesApi](#Set-up-MailgunRoutesApi)
    - [Get Routes list](#Get-Routes-list)
    - [Get single Route](#Get-single–Route)
    - [Create Route](#Create-Route)
    - [Update Route](#Update-Route)
    - [Delete Route](#Delete-Route)
  - [Webhooks](#Webhooks)
    - [Set up MailgunWebhooksApi](#Set-up-MailgunWebhooksApi)
    - [Get all Webhooks](#Get-all-Webhooks)
    - [Get Webhooks details](#Get-Webhooks-details)
    - [Create Webhook](#Create-Webhook)
    - [Update Webhook](#Update-Webhook)
    - [Delete Webhook](#Delete-Webhook)
  - [Mailing Lists](#Mailing-Lists)
    - [Set up MailgunMailingListApi](#Set-up-MailgunMailingListApi)
    - [Get Mailing List](#Get-Mailing-List)
    - [Get Mailing List by address](#Get-Mailing-List-by-address)
    - [Create Mailing List](#Create-Mailing-List)
    - [Update Mailing List](#Update-Mailing-List)
    - [Delete Mailing List](#Delete-Mailing-List)
    - [Verify Mailing List members](#Verify-Mailing-List-members)
    - [Verification job status](#Verification-job-status)
    - [Cancel verification job](#Cancel-verification-job)
    - [List of members](#List-of-members)
    - [Mailing List member](#Mailing-List-member)
    - [Add member](#Add-member)
    - [Update member](#Update-member)
    - [Add members](#Add-members)
    - [Delete member](#Delete-member)
  - [Templates](#Templates)
    - [Set up MailgunTemplatesApi](#Set-up-MailgunTemplatesApi)
    - [Get all Templates](#Get-all-Templates)
    - [Get all Templates(paging)](#Get-all-Templates(paging))
    - [Get Template](#Get-Template)
    - [Get Template Content](#Get-Template-Content)
    - [Create Template](#Create-Template)
    - [Update Template](#Update-Template)
    - [Delete Template](#Delete-Template)
    - [Delete all Templates](#Delete-all-Templates)
    - [List Template stored versions](#List-Template-stored-versions)
    - [Get specified version Template Content](#Get-specified-version-Template-Content)
    - [Create new Template version](#Create-new-Template-version)
    - [Update Template version](#Update-Template-version)
    - [Delete Template version](#Delete-Template-version)
  - [Email Validation/Verification](#Email-Validation/Verification)
    - [Set up MailgunEmailVerificationApi](#Set-up-MailgunEmailVerificationApi)
    - [Validate address](#Validate-address)
    - [Get verification job list](#Get-verification-job-list)
    - [Get verification job status](#Get-verification-job-status)
    - [Create verification job](#Create-verification-job)
    - [Cancel verification job](#Cancel-verification-job)
    - [Get preview list](#Get-preview-list)
    - [Check preview status](#Check-preview-status)
    - [Create verification preview](#Create-verification-preview)
    - [Delete verification preview](#Delete-verification-preview)
  - [Inbox Placement](#Inbox Placement)
    - [Set up MailgunSeedListApi](#Set-up-MailgunSeedListApi)
    - [Generate seed list](#Generate-seed-list)
    - [Update seed list](#Update-seed-list)
    - [Get seed lists](#Get-seed-lists)
    - [Get seed list](#Get-seed-list)
    - [Get seed list attributes](#Get-seed-list-attributes)
    - [Get seed list attribute](#Get-seed-list-attribute)
    - [Get seed list filters](#Get-seed-list-filters)
    - [Delete seed list](#Delete-seed-list)
    - [Get list results](#Get-list-results)
    - [Get results filters](#Get-results-filters)
    - [Get results attributes](#Get-results-attributes)
    - [Get results attribute](#Get-results-attribute)
    - [Get specific result](#Get-specific-result)
    - [Delete result](#Delete-result)




### Messages

[MailgunMessagesApi](https://github.com/mailgun/mailgun-java/blob/main/src/main/java/com/mailgun/api/v3/MailgunMessagesApi.java)
allows you to send emails.

[Mailgun Messages documentation](https://documentation.mailgun.com/en/latest/api-sending.html).

When you submit messages for delivery, Mailgun places them in a message queue.

#### Set up MailgunMessagesApi
```java
        MailgunMessagesApi mailgunMessagesApi = MailgunClient.config(PRIVATE_API_KEY)
                .createApi(MailgunMessagesApi.class);
```


#### Send email
```java
        Message message = Message.builder()
                .from(EMAIL_FROM)
                .to(USER_EMAIL)
                .subject(SUBJECT)
                .text(TEXT)
                .build();

        MessageResponse messageResponse = mailgunMessagesApi.sendMessage(DOMAIN, message);
```

#### Send email (name with email)
```java
        Message message = Message.builder()
                .from(EMAIL_FROM)
                .to(EmailUtil.nameWithEmail(USER_NAME, USER_EMAIL))
                .subject(SUBJECT)
                .text(TEXT)
                .build();

        MessageResponse messageResponse = mailgunMessagesApi.sendMessage(DOMAIN, message);
```

#### Send email(s)
```java
        Message message = Message.builder()
                .from(EMAIL_FROM)
                .to(Arrays.asList(USER_EMAIL_1, USER_EMAIL_2))
                .subject(SUBJECT)
                .text(TEXT)
                .build();

        MessageResponse messageResponse = mailgunMessagesApi.sendMessage(DOMAIN, message);
```
or
```java
        Message message = Message.builder()
                .from(EMAIL_FROM)
                .to(USER_EMAIL_1)
                .to(USER_EMAIL_2)
                .subject(SUBJECT)
                .text(TEXT)
                .build();

        MessageResponse messageResponse = mailgunMessagesApi.sendMessage(DOMAIN, message);
```

#### Async send email(s)

Default Async Mailgun Client configuration:
```java
        MailgunMessagesApi mailgunAsyncMessagesApi = MailgunClient.config(PRIVATE_API_KEY)
        .client(asyncClient)
        .createAsyncApi(MailgunMessagesApi.class);
```
Custom Async Mailgun Client configuration:
```java
        ExecutorService executor = Executors.newFixedThreadPool(2);
        AsyncClient.Default<Object> asyncClient = new AsyncClient.Default<>(
        new Client.Default(null, null), executor);

        MailgunMessagesApi mailgunAsyncMessagesApi = MailgunClient.config(PRIVATE_API_KEY)
        .client(asyncClient)
        .createAsyncApi(MailgunMessagesApi.class);
```
Your can create your own implementation of feign AsyncClient.

Asynchronously send email(s).
```java
        Message message = Message.builder()
                .from(EMAIL_FROM)
                .to(USER_EMAIL)
                .subject(SUBJECT)
                .text(TEXT)
                .build();

        CompletableFuture<MessageResponse> result = mailgunAsyncMessagesApi.sendMessageAsync(MAIN_DOMAIN, message);
```

#### Send email (html example)
```java
        Message message = Message.builder()
                .from(EMAIL_FROM)
                .to(EMAIL_TO)
                .subject(SUBJECT)
                .html("<html>\n" +
                "<body>\n" +
                "\t<h1>Sending HTML emails with Mailgun</h1>\n" +
                "\t<p style=\"color:blue; font-size:30px;\">Hello world</p>\n" +
                "\t<p style=\"font-size:30px;\">More examples can be found <a href=\"https://documentation.mailgun.com/en/latest/api-sending.html#examples\">here</a></p>\n" +
                "</body>\n" +
                "</html>")
                .build();

        MessageResponse messageResponse = mailgunMessagesApi.sendMessage(DOMAIN, message);
```

#### Send email (attachments example)
```java
        Message message = Message.builder()
                .from(EMAIL_FROM)
                .to(EMAIL_TO)
                .subject(SUBJECT)
                .text(TEXT)
                .attachment(new File("/path/to/file_1"))
                .attachment(new File("/path/to/file-2"))
                .attachment(Arrays.asList(new File("/path/to/file_3"), new File("/path/to/file_4")))
                .build();

        MessageResponse messageResponse = mailgunMessagesApi.sendMessage(DOMAIN, message);
```

#### Send email (attachment FormData example)
```java
        Message message = Message.builder()
                .from(EMAIL_FROM)
                .to(EMAIL_TO)
                .subject(SUBJECT)
                .text(TEXT)
                .formData(new FormData("image/png", "filename.png", pngByteArray))
                .formData(new FormData("text/plain", "filename.txt", txtByteArray))
                .formData(Arrays.asList(
                    new FormData("image/jpeg", "filename.jpeg", jpegByteArray),
                    new FormData("text/plain", "filename.txt", txtByteArray)
                    ))
                .build();

        MessageResponse messageResponse = mailgunMessagesApi.sendMessage(DOMAIN, message);
```

#### Send email (inline multiple files example)
```java
        Message message = Message.builder()
                .from(EMAIL_FROM)
                .to(EMAIL_TO)
                .subject(SUBJECT)
                .html("Text above images." +
                "<div><img height=200 id=\"1\" src=\"cid:mailgun_logo.png\"/></div>" +
                "Text between images." +
                "<div><img id=\"2\" src=\"cid:test_images.jpeg\"/></div>" +
                "Text below images.")
                .inline(MAILGUN_LOGO_FILE)
                .inline(TEST_IMAGES_FILE)
                .build();

        MessageResponse messageResponse = mailgunMessagesApi.sendMessage(DOMAIN, message);
```

#### Send email (delay example)
```java
        Message message = Message.builder()
                .from(EMAIL_FROM)
                .to(EMAIL_TO)
                .subject(SUBJECT)
                .text(TEXT)
                .deliveryTime(ZonedDateTime.now().plusMinutes(2L)) // Two minutes delay.
                .build();

        MessageResponse messageResponse = mailgunMessagesApi.sendMessage(DOMAIN, message);
```

#### Send email (reply-to example)
```java
        Message message = Message.builder()
                .from(EMAIL_FROM)
                .to(EMAIL_TO)
                .subject(SUBJECT)
                .text(TEXT)
                .replyTo(REPLY_TO_EMAIL)
                .build();

        MessageResponse messageResponse = mailgunMessagesApi.sendMessage(DOMAIN, message);
```

#### Send email (mailing list example)
```java
        Message message = Message.builder()
                .from(EMAIL_FROM)
                .to(MAILING_LIST_ADDRESS)
                .subject(SUBJECT)
                .text(TEXT)
                .build();

        MessageResponse messageResponse = mailgunMessagesApi.sendMessage(DOMAIN, message);
```

#### Send email (sender example)
```java
        Message message = Message.builder()
		.from(EMAIL_FROM)
		.to(EMAIL_TO)
		.sender(SENDER_EMAIL)
		.subject(SUBJECT)
		.text(TEXT)
		.build();

		MessageResponse messageResponse = mailgunMessagesApi.sendMessage(DOMAIN, message);
```
or sender with name and email
```java
        Message message = Message.builder()
                .from(EMAIL_FROM)
                .to(EMAIL_TO)
                .sender(EmailUtil.nameWithEmail(SENDER_NAME, SENDER_EMAIL))
                .subject(SUBJECT)
                .text(TEXT)
                .build();

        MessageResponse messageResponse = mailgunMessagesApi.sendMessage(DOMAIN, message);
```

#### Send email (with custom form property)
You can send email(s) with your own custom dynamic form property with allowed prefixes such as: ```t:, o:, h:, v:``` with the followed by any arbitrary value.
```java
        MailgunMessagesApi mailgunMessagesApi = MailgunClient.config(PRIVATE_API_KEY)
                .createApiWithRequestInterceptor(MailgunMessagesApi.class,
                        MailgunRequestInterceptor.builder()
                                .addProperty("h:Sender", EmailUtil.nameWithEmail(SENDER_NAME, SENDER_EMAIL))
                                .addProperty("h:X-My-Header", "my_custom_header")
                                .build()
                );

        Message message = Message.builder()
                .from(EMAIL_FROM)
                .to(EMAIL_TO)
                .subject(SUBJECT)
                .text(TEXT)
                .build();

        MessageResponse messageResponse = mailgunMessagesApi.sendMessage(DOMAIN, message);
```

#### Send MIME email(s)
Send email(s) in MIME format
```java
        MailgunMimeMessage mailgunMimeMessage = MailgunMimeMessage.builder()
            .to(EMAIL_TO)
            .message(new File("/path/to/file.mime"))
            .build();

        MessageResponse result = mailgunMessagesApi.sendMIMEMessage(MAIN_DOMAIN, mailgunMimeMessage);
```

More examples - [MailgunMessagesIntegrationTest](https://github.com/mailgun/mailgun-java/blob/main/src/test/java/com/mailgun/integration/MailgunMessagesIntegrationTest.java)


### Store Messages

[MailgunStoreMessagesApi](https://github.com/mailgun/mailgun-java/blob/main/src/main/java/com/mailgun/api/v3/MailgunStoreMessagesApi.java)
allows you to work with stored messages.

[Mailgun Store Messages documentation](https://documentation.mailgun.com/en/latest/api-sending.html).

#### Set up MailgunStoreMessagesApi
```java
        MailgunStoreMessagesApi mailgunStoreMessagesApi = MailgunClient.config(storedMessageUrl, PRIVATE_API_KEY)
            .createApiWithAbsoluteUrl(MailgunStoreMessagesApi.class);
```

#### Resend email(s)
```java
        MessageResponse result = mailgunStoreMessagesApi.resendMessage(EMAIL_TO);
```

#### Retrieve email(s)
```java
        StoreMessageResponse result = mailgunStoreMessagesApi.retrieveMessage();
```

More examples - [MailgunStoreMessagesIntegrationTest](https://github.com/mailgun/mailgun-java/blob/main/src/test/java/com/mailgun/integration/MailgunStoreMessagesIntegrationTest.java)



### Domains

[MailgunDomainsApi](https://github.com/mailgun/mailgun-java/blob/main/src/main/java/com/mailgun/api/v3/MailgunDomainsApi.java)
allows you to create, access, and validate domains programmatically. 

[Mailgun Domains documentation](https://documentation.mailgun.com/en/latest/api-domains.html).

#### Set up MailgunDomainsApi
```java
        MailgunDomainsApi mailgunDomainsApi = MailgunClient.config(PRIVATE_API_KEY)
                .createApi(MailgunDomainsApi.class);
```

#### Get Domains list

Returns a list of domains under your account (limit to 100 entries).
```java
        DomainListResponse domainListResponse = mailgunDomainsApi.getDomainsList();
```

#### Get single Domains

Returns a single domain, including credentials and DNS records.
Returns a list of domains under your account with filters.
```java
       SingleDomainResponse singleDomainResponse = mailgunDomainsApi.getSingleDomain(DOMAIN);
```

#### Verify Domain

Verifies and returns a single domain, including credentials and DNS records.

If the domain is successfully verified, the domain's state will be `active`.
```java
       DomainResponse domainResponse = mailgunDomainsApi.verifyDomain(DOMAIN);
```
For more information on verifying domains, visit [Verifying domain documentation](https://documentation.mailgun.com/en/latest/user_manual.html#verifying-your-domain)

#### Create Domain

Create a new domain.
```java
       DomainRequest request = DomainRequest.builder()
                .name(DOMAIN_NAME)
                .spamAction(SpamAction.BLOCK)
                .wildcard(true)
                .forceDkimAuthority(false)
                .dkimKeySize(1024)
                .ips(Arrays.asList(IP_1, IP_2))
                .webScheme(WebScheme.HTTPS)
                .build();

        DomainResponse domainResponse = mailgunDomainsApi.createNewDomain(request);
```

#### Delete Domain

Delete a domain from your account.
```java
       ResponseWithMessage response = mailgunDomainsApi.deleteDomain(DOMAIN_NAME);
```

#### Create credentials

Creates a new set of SMTP credentials for the defined domain.
```java
       DomainCredentials domainCredentials = DomainCredentials.builder()
                .login(LOGIN)
                .password(PASSWORD)
                .build();

        ResponseWithMessage response = mailgunDomainsApi.createNewCredentials(DOMAIN_NAME, domainCredentials);
```

#### Update credentials

Updates the specified SMTP credentials.

Currently, only the password can be changed.
```java
       ResponseWithMessage response = mailgunDomainsApi.updateCredentials(DOMAIN_NAME, LOGIN, PASSWORD);
```

#### Delete credentials

Deletes the defined SMTP credentials.
```java
       ResponseWithMessage response = mailgunDomainsApi.deleteCredentials(DOMAIN_NAME, LOGIN);
```

#### Get connection settings

Returns delivery connection settings for the defined domain.
```java
       DomainConnectionResponse response = mailgunDomainsApi.getDomainConnectionSettings(DOMAIN_NAME);
```

#### Update connection settings

Updates the specified delivery connection settings for the defined domain.
```java
        DomainConnectionRequest domainConnection = DomainConnectionRequest.builder()
                .requireTls(false)
                .skipVerification(false)
                .build();

        UpdateDomainConnectionResponse response = mailgunDomainsApi.updateDomainConnectionSettings(DOMAIN_NAME, domainConnection);
```

#### Get tracking settings

Returns tracking settings for a domain.
```java
       DomainTrackingResponse response = mailgunDomainsApi.getDomainTrackingSettings(TEST_DOMAIN_NAME);
```

#### Update tracking settings

Updates the open tracking settings for a domain.
```java
       UpdateDomainOpenTrackingSettingsResponse response = mailgunDomainsApi.updateDomainOpenTrackingSettings(TEST_DOMAIN_NAME, YesNo.NO);
```

#### Update click tracking settings

Updates the click tracking settings for a domain.
```java
       UpdateDomainClickTrackingSettingsResponse response = mailgunDomainsApi.updateDomainClickTrackingSettings(TEST_DOMAIN_NAME, YesNoHtml.HTML_ONLY);
```

#### Update unsubscribe tracking settings

Updates unsubscribe tracking settings for a domain.
```java
        DomainUnsubscribeConnectionSettingsRequest request = DomainUnsubscribeConnectionSettingsRequest.builder()
                .active(false)
                .htmlFooter("\n<br>\n<p><a href=\\\"%unsubscribe_url%\\\">unsubscribe java</a></p>\n")
                .textFooter("\n\nTo unsubscribe from java click: <%unsubscribe_url%>\n\n")
                .build();

        UpdateDomainUnsubscribeTrackingSettingsResponse response = mailgunDomainsApi.updateDomainUnsubscribeConnectionSettings(TEST_DOMAIN_NAME, request);
```
More examples - [MailgunDomainsIntegrationTest](https://github.com/mailgun/mailgun-java/blob/main/src/test/java/com/mailgun/integration/MailgunDomainsIntegrationTest.java)


### IPs

[MailgunIPsApi](https://github.com/mailgun/mailgun-java/blob/main/src/main/java/com/mailgun/api/v3/MailgunIPsApi.java)
allows you to access information regarding the IPs allocated to your Mailgun account used for outbound sending.

[Mailgun IPs documentation](https://documentation.mailgun.com/en/latest/api-ips.html).

#### Set up MailgunIPsApi
```java
        MailgunIPsApi mailgunIPsApi = MailgunClient.config(PRIVATE_API_KEY)
                .createApi(MailgunIPsApi.class);
```

#### Get all IPs

Returns a list of IPs assigned to your account.
```java
        IPsResult result = mailgunIPsApi.getAllIPs();
``` 

#### Get dedicated IPs

Return only dedicated IPs if <code>param</code> <code>dedicated</code> set to <code>true</code>, otherwise return all IPs.
```java
        IPsResult result = mailgunIPsApi.getDedicatedIPs(true);
``` 

#### Get specified IP

Returns information about the specified IP.
```java
        IPResult result = mailgunIPsApi.getSpecifiedIP(IP);
``` 

#### Get domain IP

Returns a list of IPs currently assigned to the specified domain.
```java
        IPsResult result = mailgunIPsApi.getDomainIPs(DOMAIN);
``` 

#### Assign IP to domain

Assign a dedicated IP to the domain specified.

**Note:** Only dedicated IPs can be assigned to a domain.
```java
        ResponseWithMessage response = mailgunIPsApi.assignIPToDomain(DOMAIN, IP);
``` 

#### Unassign IP from domain

Unassign an IP from the domain specified.
```java
        ResponseWithMessage response = mailgunIPsApi.unassignIPFromDomain(DOMAIN, IP);
``` 
More examples - [MailgunIPsIntegrationTest](https://github.com/mailgun/mailgun-java/blob/main/src/test/java/com/mailgun/integration/MailgunIPsIntegrationTest.java)


### Events

[MailgunEventsApi](https://github.com/mailgun/mailgun-java/blob/main/src/main/java/com/mailgun/api/v3/MailgunEventsApi.java)
Mailgun tracks every event that happens to your emails and makes this data available to you through the Events API.

[Mailgun Events documentation](https://documentation.mailgun.com/en/latest/api-events.html).

#### Set up MailgunEventsApi
```java
        MailgunEventsApi mailgunEventsApi = MailgunClient.config(PRIVATE_API_KEY)
                .createApi(MailgunEventsApi.class);
```

#### Get all Events

Get all events that happen to your emails.
```java
        EventsResponse response = mailgunEventsApi.getAllEvents(DOMAIN);
``` 

#### Get specified Events

Get specified events that happen to your emails.
```java
        EventsQueryOptions eventsQueryOptions = EventsQueryOptions.builder()
                .event(EventType.DELIVERED)
                .build();

        EventsResponse response = mailgunEventsApi.getEvents(DOMAIN, eventsQueryOptions);
``` 

#### Get Events next page

Fetches the next page of log records, assuming that the previous request returned the <code>pageId</code>.
```java
        EventsResponse response = mailgunEventsApi.getEvents(DOMAIN, pageId);
``` 


More examples - [MailgunEventsIntegrationTest](https://github.com/mailgun/mailgun-java/blob/main/src/test/java/com/mailgun/integration/MailgunEventsIntegrationTest.java)


### Stats

[MailgunStatisticsApi](https://github.com/mailgun/mailgun-java/blob/main/src/main/java/com/mailgun/api/v3/MailgunStatisticsApi.java)
Mailgun collects many different events and generates event statistics available via this API.

[Mailgun Statistics documentation](https://documentation.mailgun.com/en/latest/api-stats.html).

#### Set up MailgunStatisticsApi
```java
        MailgunStatisticsApi mailgunStatisticsApi = MailgunClient.config(PRIVATE_API_KEY)
                .createApi(MailgunStatisticsApi.class);
```

#### Get domain Statistics

Returns total statistics for a given domain.
```java
        StatisticsOptions statsOptions = StatisticsOptions.builder()
                .start(ZonedDateTime.now().minusDays(3))
                .end(ZonedDateTime.now())
                .event(Arrays.asList(StatsEventType.ACCEPTED, StatsEventType.DELIVERED))
                .build();

        StatsResult result = mailgunStatisticsApi.getDomainStats(DOMAIN, statsOptions);
```

More examples - [MailgunStatisticsIntegrationTest](https://github.com/mailgun/mailgun-java/blob/main/src/test/java/com/mailgun/integration/MailgunStatisticsIntegrationTest.java)



### Tags

[MailgunTagsApi](https://github.com/mailgun/mailgun-java/blob/main/src/main/java/com/mailgun/api/v3/MailgunTagsApi.java)
Mailgun lets you tag each outgoing message with a custom value and provides statistics on the tag level.

[Mailgun Tags documentation](https://documentation.mailgun.com/en/latest/api-tags.html).

#### Set up MailgunTagsApi
```java
        MailgunTagsApi mailgunTagsApi = MailgunClient.config(PRIVATE_API_KEY)
                .createApi(MailgunTagsApi.class);
```

#### Get all Tags

Returns a list of tags for a domain.
```java
        TagsResult result = mailgunTagsApi.getAllTags(DOMAIN);
``` 

#### Get Tag

Returns a given tag.
```java
        TagsItem result = mailgunTagsApi.getTag(DOMAIN, TAG);
``` 

#### Update Tag

Updates a given tag with the information provided.
```java
        TagUpdateRequest request = TagUpdateRequest.builder()
                .description(newDescription)
                .build();

        ResponseWithMessage response = mailgunTagsApi.updateTag(DOMAIN, TAG, request);
``` 

#### Get Tag statistics

Returns statistics for a given tag.
```java
        StatisticsOptions statisticsOptions = StatisticsOptions.builder()
                .event(Arrays.asList(StatsEventType.ACCEPTED, StatsEventType.DELIVERED))
                .resolution(ResolutionPeriod.DAY)
                .duration(3, Duration.DAY)
                .build();

        TagStatsResult result = mailgunTagsApi.getTagStatistics(DOMAIN, TAG, statisticsOptions);
``` 

#### Delete Tag

Deletes the tag.
```java
        ResponseWithMessage response = mailgunTagsApi.deleteTag(DOMAIN, TAG);
``` 

#### List Tag countries

Returns a list of countries of origin for a given domain for different event types.
```java
        TagCountriesResponse response = mailgunTagsApi.listTagCountries(DOMAIN, TAG);
``` 

#### List Tag providers

Returns a list of email providers for a given domain for different event types.
```java
        TagProvidersResponse response = mailgunTagsApi.listTagProviders(DOMAIN, TAG);
``` 

#### List Tag devices

Returns a list of devices for a given domain that have triggered event types.
```java
        TagDevicesResponse response = mailgunTagsApi.listTagDevices(DOMAIN, TAG);
``` 

More examples - [MailgunTagsApiIntegrationTest](https://github.com/mailgun/mailgun-java/blob/main/src/test/java/com/mailgun/integration/MailgunTagsApiIntegrationTest.java)



### Suppressions

[Mailgun Suppressions documentation](https://documentation.mailgun.com/en/latest/api-suppressions.html).


### Suppression Bounces

[MailgunSuppressionBouncesApi](https://github.com/mailgun/mailgun-java/blob/main/src/main/java/com/mailgun/api/v3/suppression/MailgunSuppressionBouncesApi.java)

[Mailgun Suppression Bounces documentation](https://documentation.mailgun.com/en/latest/api-suppressions.html#bounces).

#### Set up MailgunSuppressionBouncesApi
```java
        MailgunSuppressionBouncesApi mailgunSuppressionBouncesApi = MailgunClient.config(PRIVATE_API_KEY)
                .createApi(MailgunSuppressionBouncesApi.class);
```

#### Get Bounces

Returns a list of bounces for a domain.
```java
        BouncesResponse response = suppressionBouncesApi.getBounces(DOMAIN, MAXIMUM_NUMBER_OF_RECORDS);
``` 

#### Get Bounce

Fetch a single bounce event by a given email address.

Helpful to check if a given email address has bounced before.
```java
        BouncesItem response = suppressionBouncesApi.getBounce(DOMAIN, ADDRESS);
``` 

#### Add Bounce

Add a single bounce record to the bounce list.

Updates the existing record if the address is already there.
```java
        BouncesRequest bouncesRequest = BouncesRequest.builder()
                .address(ADDRESS)
                .code(CODE)
                .error(ERROR_MESSAGE)
                .createdAt(DATE_TIME)
                .build();

        SuppressionResponse response = suppressionBouncesApi.addBounce(DOMAIN, bouncesRequest);
``` 

#### Add Bounces

Add multiple bounce records to the bounce list in a single API call.
```java
        BouncesRequest bouncesRequest1 = BouncesRequest.builder()
                .address(ADDRESS_1)
                .code(CODE)
                .error(ERROR_MESSAGE)
                .createdAt(DATE_TIME)
                .build();

        BouncesRequest bouncesRequest2 = BouncesRequest.builder()
                .address(ADDRESS_2)
                .code(CODE)
                .error(ERROR_MESSAGE)
                .createdAt(DATE_TIME)
                .build();

        ResponseWithMessage response = suppressionBouncesApi.addBounces(DOMAIN, Arrays.asList(bouncesRequest1, bouncesRequest2));
``` 

#### Import Bounces

Import a list of bounces.
```java
        BouncesListImportRequest request = BouncesListImportRequest.builder()
            .file(new File("/path/to/file"))
            .build();

        ResponseWithMessage result = suppressionBouncesApi.importBounceList(MAIN_DOMAIN, request);
``` 

#### Delete Bounce

Delete a single bounce.
```java
        ResponseWithMessage response = suppressionBouncesApi.deleteBounce(DOMAIN, ADDRESS);
``` 

#### Delete all Bounces

Delete all bounced email addresses for a domain.
```java
        ResponseWithMessage response = suppressionBouncesApi.deleteAllBounces(DOMAIN);
``` 


More examples - [MailgunSuppressionBouncesApiIntegrationTest](https://github.com/mailgun/mailgun-java/blob/main/src/test/java/com/mailgun/integration/MailgunSuppressionBouncesApiIntegrationTest.java)




### Suppression Complaints

[MailgunSuppressionComplaintsApi](https://github.com/mailgun/mailgun-java/blob/main/src/main/java/com/mailgun/api/v3/suppression/MailgunSuppressionComplaintsApi.java)

[Mailgun Suppression Complaints documentation](https://documentation.mailgun.com/en/latest/api-suppressions.html#complaints).

#### Set up MailgunSuppressionComplaintsApi
```java
        MailgunSuppressionComplaintsApi mailgunSuppressionComplaintsApi = MailgunClient.config(PRIVATE_API_KEY)
                .createApi(MailgunSuppressionComplaintsApi.class);
```

#### Get all Complaints

Returns a list of complaints for a domain.
```java
        ComplaintsItemResponse response = suppressionComplaintsApi.getAllComplaints(DOMAIN, MAXIMUM_NUMBER_OF_RECORDS);
``` 

#### Get single Complaint

Fetch a single spam complaint by a given email address. 

Helpful to check if a particular user has complained.
```java
        ComplaintsItem response = suppressionComplaintsApi.getSingleComplaint(DOMAIN, EMAIL);
``` 

#### Add Address

Add an address to the complaints list.
```java
        ComplaintsSingleItemRequest request = ComplaintsSingleItemRequest.builder()
                .address(EMAIL)
                .createdAt(DATE_TIME)
                .build();

        SuppressionResponse response = suppressionComplaintsApi.addAddressToComplaintsList(DOMAIN, request);
``` 

#### Add Addresses

Add multiple complaint records to the complaint list in a single API call(up to 1000 complaint records).
```java
        ComplaintsItem complaintsItem1 = ComplaintsItem.builder()
                .address(EMAIL_1)
                .createdAt(DATE_TIME)
                .build();

        ComplaintsItem complaintsItem2 = ComplaintsItem.builder()
                .address(EMAIL_2)
                .build();

        ResponseWithMessage response = suppressionComplaintsApi.addAddressesToComplaintsList(MAIN_DOMAIN, Arrays.asList(complaintsItem1, complaintsItem2));
``` 

#### Import Addresses

Import a list of complaints.
```java
        ComplaintsListImportRequest request = ComplaintsListImportRequest.builder()
            .file(new File("/path/to/file"))
            .build();

        ResponseWithMessage result = suppressionComplaintsApi.importComplaintsList(MAIN_DOMAIN, request);
``` 

#### Remove Address

Remove Address From Complaints.
```java
        SuppressionResponse response = suppressionComplaintsApi.removeAddressFromComplaints(DOMAIN, EMAIL);
``` 


More examples - [MailgunSuppressionComplaintsApiIntegrationTest](https://github.com/mailgun/mailgun-java/blob/main/src/test/java/com/mailgun/integration/MailgunSuppressionComplaintsApiIntegrationTest.java)




### Suppression Unsubscribe

[MailgunSuppressionUnsubscribeApi](https://github.com/mailgun/mailgun-java/blob/main/src/main/java/com/mailgun/api/v3/suppression/MailgunSuppressionUnsubscribeApi.java)

[Mailgun Suppression Unsubscribe documentation](https://documentation.mailgun.com/en/latest/api-suppressions.html#unsubscribes).

#### Set up MailgunSuppressionUnsubscribeApi
```java
        MailgunSuppressionUnsubscribeApi mailgunSuppressionUnsubscribeApi = MailgunClient.config(PRIVATE_API_KEY)
                .createApi(MailgunSuppressionUnsubscribeApi.class);
```

#### Get all Unsubscribe

Returns a list of unsubscribes for a domain.
```java
        UnsubscribeItemResponse response = suppressionUnsubscribeApi.getAllUnsubscribe(DOMAIN, MAXIMUM_NUMBER_OF_RECORDS);
``` 

#### Get single Unsubscribe

Fetch a single unsubscribe record. 

Can be used to check if a given address is present in the list of unsubscribed users.
```java
        UnsubscribeItem response = suppressionUnsubscribeApi.getSingleUnsubscribe(DOMAIN, EMAIL);
``` 

#### Add address

Add an address to the unsubscribe table.
```java
        UnsubscribeSingleItemRequest request = UnsubscribeSingleItemRequest.builder()
                .address(EMAIL)
                .tag(TAG)
                .createdAt(DATE_TIME)
                .build();

        SuppressionResponse response = suppressionUnsubscribeApi.addAddressToUnsubscribeTable(DOMAIN, request);
``` 

#### Add addresses

Add multiple unsubscribe records to the unsubscribe list in a single API call(up to 1000 unsubscribe records).
```java
        UnsubscribeItem unsubscribeItemAllFields = UnsubscribeItem.builder()
        .address(EMAIL_1)
        .tags(Arrays.asList(TAG_1, TAG_2))
        .createdAt(DATE_TIME)
        .build();

        UnsubscribeItem unsubscribeItemAddressOnly = UnsubscribeItem.builder()
        .address(EMAIL_2)
        .build();

        ResponseWithMessage response = suppressionUnsubscribeApi.addAddressesToUnsubscribeTable(DOMAIN, Arrays.asList(unsubscribeItemAllFields, unsubscribeItemAddressOnly));
``` 

#### Import addresses

Import a CSV file containing a list of addresses to add to the unsubscribe list.
```java
        UnsubscribesListImportRequest request = UnsubscribesListImportRequest.builder()
        .file(new File("/path/to/file"))
        .build();

        ResponseWithMessage result = suppressionUnsubscribeApi.importAddressesToUnsubscribeTable(MAIN_DOMAIN, request);
``` 

#### Remove address tag

Remove an address from the unsubscribes list.
```java
        SuppressionResponse response = suppressionUnsubscribeApi.removeAddressFromUnsubscribeTag(DOMAIN, EMAIL, TAG);
``` 

#### Remove address

Completely remove an address from the unsubscribes list.
```java
        SuppressionResponse response = suppressionUnsubscribeApi.removeAddressFromUnsubscribeList(DOMAIN, EMAIL);
``` 


More examples - [MailgunSuppressionUnsubscribeApiIntegrationTest](https://github.com/mailgun/mailgun-java/blob/main/src/test/java/com/mailgun/integration/MailgunSuppressionUnsubscribeApiIntegrationTest.java)



### Suppression Whitelists

[MailgunSuppressionWhitelistsApi](https://github.com/mailgun/mailgun-java/blob/main/src/main/java/com/mailgun/api/v3/suppression/MailgunSuppressionWhitelistsApi.java)

[Mailgun Suppression Whitelists documentation](https://documentation.mailgun.com/en/latest/api-suppressions.html#whitelists).

#### Set up MailgunSuppressionWhitelistsApi
```java
        MailgunSuppressionWhitelistsApi mailgunSuppressionWhitelistsApi = MailgunClient.config(PRIVATE_API_KEY)
                .createApi(MailgunSuppressionWhitelistsApi.class);
```

#### Get all Whitelists

Returns a list of whitelists for a domain.
```java
        WhitelistsItemResponse response = suppressionWhitelistsApi.getAllWhitelists(DOMAIN, MAXIMUM_NUMBER_OF_RECORDS);
``` 

#### Get single Whitelist

Fetch a single whitelist record.

Can be used to check if a given address or domain is present in the whitelist table.
```java
        WhitelistsItem response = suppressionWhitelistsApi.getSingleWhitelistRecord(DOMAIN, EMAIL);
``` 

#### Add address/domain

Add an address or domain to the whitelist table.

**Note:** The single request accepts either one address or domain parameter.
```java
        WhitelistsRequest request = WhitelistsRequest.builder()
                .address(EMAIL)
                .reason(REASON)
                .build();

        ResponseWithMessage response = suppressionWhitelistsApi.addSingleWhitelistRecord(DOMAIN, request);
``` 

#### Import address/domain

Import a CSV file containing a list of addresses and/or domains to add to the whitelist.
```java
        WhitelistsListImportRequest request = WhitelistsListImportRequest.builder()
        .file(new File("/path/to/file"))
            .build();

        ResponseWithMessage result = suppressionWhitelistsApi.importWhitelistRecords(MAIN_DOMAIN, request);
``` 

#### Delete address/domain

Delete a single record from whitelist table.
```java
        WhitelistsRemoveRecordResponse response = suppressionWhitelistsApi.removeRecordFromWhitelists(DOMAIN, EMAIL);
``` 

More examples - [MailgunSuppressionWhitelistsApiIntegrationTest](https://github.com/mailgun/mailgun-java/blob/main/src/test/java/com/mailgun/integration/MailgunSuppressionWhitelistsApiIntegrationTest.java)




### Routes

[MailgunRoutesApi](https://github.com/mailgun/mailgun-java/blob/main/src/main/java/com/mailgun/api/v3/MailgunRoutesApi.java)
allows you to work with routes programmatically.

[Mailgun Routes documentation](https://documentation.mailgun.com/en/latest/api-routes.html).

#### Set up MailgunRoutesApi
```java
        MailgunRoutesApi mailgunRoutesApi = MailgunClient.config(PRIVATE_API_KEY)
                .createApi(MailgunRoutesApi.class);
```

#### Get Routes list

Fetches the list of routes.
```java
        RoutesPageRequest pageRequest = RoutesPageRequest.builder()
                .limit(2)
                .skip(2)
                .build();

        RoutesListResponse response = mailgunRoutesApi.getRoutesList(pageRequest);
``` 

#### Get single Route

Returns a single route object based on its ID.
```java
        SingleRouteResponse response = mailgunRoutesApi.getSingleRoute(ROUTE_ID);
``` 

#### Create Route

Creates a new route.
```java
        RoutesRequest routesRequest = RoutesRequest.builder()
                .priority(2)
                .description(DESCRIPTION)
                .expression("match_recipient('.*some-address-@example.com')")
                .action("forward('" + EMAIL_2 + "')")
                .action("forward('" + EMAIL_3 + "')")
                .actions(Arrays.asList("forward('https://myhost.com/messages')", "stop()"))
                .build();

        RoutesResponse response = mailgunRoutesApi.createRoute(routesRequest);
``` 

#### Update Route

Updates a given route by ID.
```java
        RoutesRequest routesRequest = RoutesRequest.builder()
                .priority(1)
                .action("forward('" + EMAIL + "')")
                .build();

        Route result = mailgunRoutesApi.updateRoute(routeId, routesRequest);
``` 

#### Delete Route

Deletes a route based on the id.
```java
        ResponseWithMessage response = mailgunRoutesApi.deleteRoute(ROUTE_ID);
``` 

More examples - [MailgunRoutesIntegrationTest](https://github.com/mailgun/mailgun-java/blob/main/src/test/java/com/mailgun/integration/MailgunRoutesIntegrationTest.java)



### Webhooks

[MailgunWebhooksApi](https://github.com/mailgun/mailgun-java/blob/main/src/main/java/com/mailgun/api/v3/MailgunWebhooksApi.java) 
allows you to create, access, and delete webhooks programmatically.

[Mailgun Webhooks documentation](https://documentation.mailgun.com/en/latest/api-webhooks.html).

#### Set up MailgunWebhooksApi
```java
        MailgunWebhooksApi mailgunWebhooksApi = MailgunClient.config(PRIVATE_API_KEY)
                .createApi(MailgunWebhooksApi.class);
```

#### Get all Webhooks

Returns a list of webhooks set for the specified domain.
```java
        WebhookListResult result = mailgunWebhooksApi.getAllWebhooks(DOMAIN);
``` 

#### Get Webhooks details

Return details about a webhook specified in the URL.
```java
        WebhookDetailsResult result = mailgunWebhooksApi.getWebhookDetails(DOMAIN, WebhookName.CLICKED);
``` 

#### Create Webhook

Creates a new webhook.
```java
        WebhookRequest request = WebhookRequest.builder()
                .webhookName(WebhookName.CLICKED)
                .url(WEBHOOK_URL)
                .build();

        WebhookResult result = mailgunWebhooksApi.createNewWebhook(DOMAIN, request);
``` 

#### Update Webhook

Updates an existing webhook.
```java
        WebhookUpdateRequest request = WebhookUpdateRequest.builder()
                .urls(Arrays.asList(WEBHOOK_URL_2, WEBHOOK_URL_3))
                .build();

        WebhookResult result = mailgunWebhooksApi.updateWebhook(MAIN_DOMAIN, WebhookName.CLICKED, request);
``` 

#### Delete Webhook

Deletes an existing webhook.
```java
        WebhookResult result = mailgunWebhooksApi.deleteWebhook(DOMAIN, WebhookName.CLICKED);
``` 

More examples - [MailgunWebhooksApiIntegrationTest](https://github.com/mailgun/mailgun-java/blob/main/src/test/java/com/mailgun/integration/MailgunWebhooksApiIntegrationTest.java)



### Mailing Lists

You can programmatically create mailing lists using [MailgunMailingListApi](https://github.com/mailgun/mailgun-java/blob/main/src/main/java/com/mailgun/api/v3/MailgunMailingListApi.java)

[Mailgun Mailing Lists documentation](https://documentation.mailgun.com/en/latest/api-mailinglists.html).

#### Set up MailgunMailingListApi
```java
        MailgunMailingListApi mailgunMailingListApi = MailgunClient.config(PRIVATE_API_KEY)
                .createApi(MailgunMailingListApi.class);
```

#### Get Mailing List

Returns mailing lists under your account.
```java
        MailingListDataResponse response = mailgunMailingListApi.getMailingList(MAXIMUM_NUMBER_OF_RECORDS);
``` 

#### Get Mailing List by address

Returns a single mailing list by a given address.
```java
        SingleMailingListResponse response = mailgunMailingListApi.getMailingListByAddress(ADDRESS);
``` 

#### Create Mailing List

Creates a new mailing list.
```java
        MailingListRequest request = MailingListRequest.builder()
                .address(MAILING_LIST_ADDRESS)
                .name(MAILING_LIST_NAME)
                .description(DESCRIPTION)
                .accessLevel(AccessLevel.EVERYONE)
                .replyPreference(ReplyPreference.LIST)
                .build();

        MailingListResponse response = mailgunMailingListApi.createMailingList(request);
``` 

#### Update Mailing List

Update mailing list properties, such as <code>address</code>, <code>description</code> or <code>name</code>.
```java
        UpdateMailingListRequest request = UpdateMailingListRequest.builder()
                .address(NEW_ADDRESS)
                .name(NEW_MAILING_LIST_NAME)
                .description(NEW_DESCRIPTION)
                .accessLevel(AccessLevel.MEMBERS)
                .replyPreference(ReplyPreference.SENDER)
                .build();

        MailingListResponse response = mailgunMailingListApi.updateMailingList(MAILING_LIST_ADDRESS, request);
``` 

#### Delete Mailing List

Deletes a mailing list.
```java
        DeleteMailingListResponse response = mailgunMailingListApi.deleteMailingList(MAILING_LIST_ADDRESS);
``` 

#### Verify Mailing List members

Verify all the members of the mailing list.
```java
        MailingListVerificationResponse response = mailgunMailingListApi.verifyMailingListMembers(MAILING_LIST_ADDRESS);
``` 

#### Verification job status

Retrieve the current status of the mailing list verification job.
```java
        MailingListVerificationStatusResponse response = mailgunMailingListApi.getMailingListVerificationJobStatus(MAILING_LIST_ADDRESS);
``` 

#### Cancel verification job

Cancel an active mailing list verification job.
```java
        String result = mailgunMailingListApi.cancelActiveMailingListVerificationJob(MAILING_LIST_ADDRESS);
``` 

#### List of members

Returns the list of members in the given mailing list.
```java
        MailingListMembersRequest request = MailingListMembersRequest.builder()
            .limit(10)
            .build();

        MailingListMembersResponse response = mailgunMailingListApi.getMailingListMembers(MAILING_LIST_ADDRESS, request);
``` 

Returns the first page of the list of members in the given mailing list.
```java
        MailingListMembersRequest request = MailingListMembersRequest.builder()
                .limit(10)
                .page("first")
                .build();

        MailingListMembersResponse response = mailgunMailingListApi.getMailingListMembers(MAILING_LIST_ADDRESS, request);
``` 

Returns the last page of the list of members in the given mailing list.
```java
        MailingListMembersRequest request = MailingListMembersRequest.builder()
                .limit(10)
                .page("last")
                .build();

        MailingListMembersResponse response = mailgunMailingListApi.getMailingListMembers(MAILING_LIST_ADDRESS, request);
``` 

Returns the next page after specified email of the list of members in the given mailing list.
```java
        MailingListMembersResponse response = mailgunMailingListApi.getMailingListMembers(MAILING_LIST_ADDRESS, request);

        memberAddress = response.getItems().stream()
                .reduce((first, last) -> last)
                .orElseThrow(NoSuchElementException::new)
                .getAddress();
		
        MailingListMembersRequest request = MailingListMembersRequest.builder()
                .limit(10)
                .page("next")
                .address(memberAddress)
                .build();

        response = mailgunMailingListApi.getMailingListMembers(MAILING_LIST_ADDRESS, request);
``` 

Returns the previous page before specified email of the list of members in the given mailing list.
```java
        MailingListMembersResponse response = mailgunMailingListApi.getMailingListMembers(MAILING_LIST_ADDRESS, request);

        memberAddress = response.getItems().stream()
                .findFirst()
                .orElseThrow(NoSuchElementException::new)
                .getAddress();

        MailingListMembersRequest request = MailingListMembersRequest.builder()
                .limit(10)
                .page("prev")
                .address(memberAddress)
                .build();
        
        response = mailgunMailingListApi.getMailingListMembers(MAILING_LIST_ADDRESS, request);
``` 

#### Mailing List member

Retrieves a mailing list member.
```java
        MailingListMemberResponse response = mailgunMailingListApi.getMailingListMember(MAILING_LIST_ADDRESS, MEMBER_EMAIL);
``` 

#### Add member

Adds a member to the mailing list.
```java
        MailingListNewMemberRequest request = MailingListNewMemberRequest.builder()
                .address(MEMBER_EMAIL)
                .name(MEMBER_NAME)
                .vars(MAP_OF_PARAMETERS)
                .subscribed(true)
                .build();

        MailingListMemberResponse response = mailgunMailingListApi.addMemberToMailingList(MAILING_LIST_ADDRESS, request);
``` 

#### Update member

Updates a mailing list member with given properties. Won't touch the property if it's not passed in.
```java
        MailingListMemberUpdateRequest request = MailingListMemberUpdateRequest.builder()
                .name(NEW_NAME)
                .vars(MAP_OF_PARAMETERS)
                .subscribed(true)
                .build();

        MailingListMemberResponse response = mailgunMailingListApi.updateMailingListMember(MAILING_LIST_ADDRESS, MEMBER_EMAIL, request);
``` 

#### Add members

Adds multiple members, up to 1,000 per call, to a Mailing List.
```java
        MailingListMember mailingListMember_1 = MailingListMember.builder()
                .address(MEMBER_1_EMAIL)
                .name(MEMBER_NAME)
                .vars(MAP_OF_PARAMETERS)
                .subscribed(true)
                .build();

        MailingListMember mailingListMember_2 = MailingListMember.builder()
                .address(MEMBER_2_EMAIL)
                .name(MEMBER_NAME)
                .vars(MAP_OF_PARAMETERS)
                .subscribed(false)
                .build();

        AddMailingListMembersRequest request = AddMailingListMembersRequest.builder()
                .members(Arrays.asList(mailingListMember_1, mailingListMember_2))
                .upsert(true)
                .build();

        MailingListResponse response = mailgunMailingListApi.addMembersToMailingList(MAILING_LIST_ADDRESS, request);
``` 

#### Delete member

Delete a mailing list member.
```java
        MailingListMemberResponse response = mailgunMailingListApi.deleteMemberFromMailingList(MAILING_LIST_ADDRESS, MEMBER_EMAIL);
``` 

More examples - [MailgunMailingListApiIntegrationTest](https://github.com/mailgun/mailgun-java/blob/main/src/test/java/com/mailgun/integration/MailgunMailingListApiIntegrationTest.java)




### Templates

[MailgunTemplatesApi](https://github.com/mailgun/mailgun-java/blob/main/src/main/java/com/mailgun/api/v3/MailgunTemplatesApi.java)
allows you to access information regarding the IPs allocated to your Mailgun account that is used for outbound sending.

[Mailgun Templates documentation](https://documentation.mailgun.com/en/latest/api-templates.html).

#### Set up MailgunTemplatesApi
```java
        MailgunTemplatesApi mailgunTemplatesApi = MailgunClient.config(PRIVATE_API_KEY)
                .createApi(MailgunTemplatesApi.class);
```

#### Get all Templates

Returns a list of stored templates for the domain (limit to 10 entries).
```java
        TemplatesResult result = mailgunTemplatesApi.getAllTemplates(DOMAIN);
``` 

#### Get all Templates(paging)

Returns a list of stored templates for the domain with paging.
```java
        PagingWithPivot pagingWithPivot = PagingWithPivot.builder()
                .limit(2)
                .page(Page.NEXT)
                .pivot(TEMPLATE_NAME)
                .build();

        TemplatesResult result = mailgunTemplatesApi.getAllTemplates(MAIN_DOMAIN, pagingWithPivot);
``` 

#### Get Template

Returns metadata information about a stored template.
```java
        TemplateResponse response = mailgunTemplatesApi.getTemplate(DOMAIN, TEMPLATE_NAME);
``` 

#### Get Template Content

Returns the content of the active version of the template with metadata information.
```java
        TemplateWithVersionResponse response = mailgunTemplatesApi.getActiveTemplateVersionContent(DOMAIN, TEMPLATE_NAME);
``` 

#### Create Template

This API stores a new template, including its name, description, and (optionally) the template content.

If the template content is provided, a new version is automatically created and becomes the active version.
```java
        TemplateRequest request = TemplateRequest.builder()
                .name(TEMPLATE_NAME)
                .description(TEMPLATE_DESCRIPTION)
                .template("Hey, {{name}}!")
                .tag(TEMPLATE_VERSION_TAG)
                .engine(TEMPLATE_ENGINE)
                .comment(TEMPLATE_COMMENT)
                .build();

        TemplateWithMessageResponse response = mailgunTemplatesApi.storeNewTemplate(DOMAIN, request);
``` 

#### Update Template

Update the metadata information of the template.
```java
        TemplateStatusResponse response = mailgunTemplatesApi.updateTemplate(DOMAIN, TEMPLATE_NAME, TEMPLATE_DESCRIPTION);
``` 

#### Delete Template

Delete the template.
```java
        TemplateStatusResponse response = mailgunTemplatesApi.deleteTemplate(DOMAIN, TEMPLATE_NAME);
``` 

#### Delete all Templates

Delete all stored templates for the domain.
```java
        ResponseWithMessage response = mailgunTemplatesApi.deleteAllTemplatesInDomain(DOMAIN);
``` 

#### List Template stored versions

Returns a list of stored versions of the template.
```java
        TemplateAllVersionsResponse response = mailgunTemplatesApi.getAllTemplateVersions(DOMAIN, TEMPLATE_NAME);
``` 

#### Get specified version Template Content

Retrieve information and content of specified version of the template.
```java
        TemplateWithVersionResponse response = mailgunTemplatesApi.getSpecifiedVersionTemplateContent(DOMAIN, TEMPLATE_NAME, TEMPLATE_VERSION_TAG);
``` 

#### Create new Template version

Create a new version of a template. 

If the template does not contain any other versions, the first version becomes active.
```java
        TemplateVersionRequest request = TemplateVersionRequest.builder()
                .template(TEMPLATE)
                .tag(TEMPLATE_VERSION_TAG)
                .engine(TEMPLATE_ENGINE)
                .comment(TEMPLATE_COMMENT)
                .active(true)
                .build();

        TemplateWithMessageResponse response = mailgunTemplatesApi.createNewTemplateVersion(DOMAIN, TEMPLATE_NAME, request);
``` 

#### Update Template version

Update information or content of the specific version of the template.
```java
        UpdateTemplateVersionRequest request = UpdateTemplateVersionRequest.builder()
                .template(TEMPLATE)
                .comment(TEMPLATE_COMMENT)
                .active(true)
                .build();

        TemplateVersionResponse response = mailgunTemplatesApi.updateSpecificTemplateVersion(DOMAIN, TEMPLATE_NAME, TEMPLATE_VERSION_TAG, request);
``` 

#### Delete Template version

Delete a specific version of the template.
```java
        TemplateVersionResponse response = mailgunTemplatesApi.deleteSpecificTemplateVersion(DOMAIN, TEMPLATE_NAME, TEMPLATE_VERSION_TAG);
``` 

More examples - [MailgunTemplatesIntegrationTest](https://github.com/mailgun/mailgun-java/blob/main/src/test/java/com/mailgun/integration/MailgunTemplatesIntegrationTest.java)



### Email Validation/Verification

[MailgunEmailVerificationApi](https://github.com/mailgun/mailgun-java/blob/main/src/main/java/com/mailgun/api/v4/MailgunEmailVerificationApi.java)
is an email address verification service.

[Mailgun Email Validation/Verification documentation](https://documentation.mailgun.com/en/latest/api-email-validation.html).

#### Set up MailgunEmailVerificationApi
```java
        MailgunEmailVerificationApi mailgunEmailVerificationApi = MailgunClient.config(PRIVATE_API_KEY)
                .createApi(MailgunEmailVerificationApi.class);
```

#### Validate address

Given an arbitrary address, validates address based on defined checks.
```java
        AddressValidationResponse result = mailgunEmailVerificationApi.validateAddress(EMAIL);
```

#### Get verification job list

Get list of all bulk verification jobs.
```java
        BulkVerificationJobListResponse result = mailgunEmailVerificationApi.getBulkVerificationJobList();
```

#### Get verification job status

Check the current status of a bulk verification job.
```java
        BulkVerificationJobStatusResponse result = mailgunEmailVerificationApi.getBulkVerificationJobStatus(LIST_NAME);
```

#### Create verification job

Create a bulk verification job.
```java
        BulkVerificationStatusRequest request = BulkVerificationStatusRequest.builder()
            .file(new File("/path/to/file"))
            .build();

        BulkVerificationCreatingResponse result = mailgunEmailVerificationApi.createBulkVerificationJob(LIST_NAME, request);
```

#### Cancel verification job

Cancel current running bulk verification job.
```java
        String result = mailgunEmailVerificationApi.cancelBulkVerificationJob(LIST_NAME);
```

#### Get preview list

Get list of all bulk verification previews.
```java
        BulkVerificationPreviewListResponse result = mailgunEmailVerificationApi.getBulkVerificationPreviewList();
```

#### Check preview status

Check the current status of a bulk verification preview.
```java
        BulkVerificationPreviewResponse result = mailgunEmailVerificationApi.getBulkVerificationPreviewStatus(LIST_NAME);
```

#### Create verification preview

Create a bulk verification preview.
```java
        BulkVerificationStatusRequest request = BulkVerificationStatusRequest.builder()
            .file(new File("/path/to/file"))
            .build();

        BulkVerificationCreatingResponse result = mailgunEmailVerificationApi.createBulkVerificationPreview(LIST_NAME, request);
```

#### Delete verification preview

Delete a bulk verification preview.
```java
        Response result = mailgunEmailVerificationApi.deleteBulkVerificationPreview(LIST_NAME);
```

More examples - [MailgunEmailVerificationIntegrationTest](https://github.com/mailgun/mailgun-java/blob/main/src/test/java/com/mailgun/integration/MailgunEmailVerificationIntegrationTest.java)



### Inbox Placement

[MailgunSeedListApi](https://github.com/mailgun/mailgun-java/blob/main/src/main/java/com/mailgun/api/v4/MailgunSeedListApi.java)
A seed list is an object that provides the mailing list for your inbox placement test.
It also acts as a container for all the results of those tests and will aggregate the stats of all the tests..

[Inbox Placement documentation](https://documentation.mailgun.com/en/latest/api-inbox-placement.html).

#### Set up MailgunSeedListApi
```java
        MailgunSeedListApi mailgunSeedListApi = MailgunClient.config(PRIVATE_API_KEY)
                .createApi(MailgunSeedListApi.class);
```

#### Generate seed list

Generate a seed list
```java
        SeedListRequest request = SeedListRequest.builder()
            .seedFilter(SEED_FILTER)
            .name(SEED_LIST_NAME)
            .sendingDomains(Arrays.asList(TEST_DOMAIN_1, TEST_DOMAIN_2))
            .build();

        SeedListItem result = mailgunSeedListApi.generateSeedList(request);
```

#### Update seed list

You can update a seed list with this endpoint.
```java
        SeedListRequest request = SeedListRequest.builder()
            .seedFilter(SEED_FILTER)
            .name(SEED_LIST_NAME)
            .sendingDomains(Arrays.asList(TEST_DOMAIN_1, TEST_DOMAIN_2))
            .build();

        SeedListItem result = mailgunSeedListApi.updateSeedList(TARGET_EMAIL, request);
```

#### Get seed lists

Get a list of all of your seed lists. You can filter this using the available filters.
```java
        SeedListsPageRequest filter = SeedListsPageRequest.builder()
            .limit(2)
            .offset(1)
            .ascending(false)
            .build();

        SeedListsResponse result = mailgunSeedListApi.getAllSeedLists(filter);
```

#### Get seed list

You can select a single seed list with this endpoint.
```java
        SingleSeedListResponse result = mailgunSeedListApi.getSeedList(TARGET_EMAIL);
```

#### Get seed list attributes

Get all iterable attributes of seed lists.
```java
        SeedListsAttributesResponse result = mailgunSeedListApi.getSeedListsAttributes();
```

#### Get seed list attribute

Get all values of a specific attribute of your seed lists.
```java
        SeedListsAttributesResponse result = mailgunSeedListApi.getSeedListsAttribute(ATTRIBUTE_NAME);
```

#### Get seed list filters

Get all available filters for seed lists.
```java
        SeedListsFiltersResponse result = mailgunSeedListApi.getSeedListFilters();
```

#### Delete seed list

Delete a seed list.
```java
        Response result = mailgunSeedListApi.deleteSeedListFeignResponse(TARGET_EMAIL);
```

#### Get list results

Test results are generated when a message has been received at the target_email.
```java
        Response result = mailgunSeedListApi.getResultsFeignResponse();
```

#### Get results filters

Get Available Result Filters.
```java
        Response result = mailgunSeedListApi.getAvailableResultFiltersFeignResponse();
```

#### Get results attributes

Get all iterable attributes of results.
```java
        SeedListsAttributesResponse result = mailgunSeedListApi.getResultsAttributes();
```

#### Get results attribute

Get all values of a specific attribute of your results lists.
```java
        SeedListsAttributesResponse result = mailgunSeedListApi.getResultsAttribute(ATTRIBUTE);
```

#### Get specific result

Get a specific result.
```java
        Response result = mailgunSeedListApi.getSpecificResultFeignResponse(RID);
```

#### Delete result

Delete a result.
```java
        Response result = mailgunSeedListApi.deleteResultFeignResponse(RID);
```

More examples - [MailgunSeedListIntegrationTest](https://github.com/mailgun/mailgun-java/blob/main/src/test/java/com/mailgun/integration/MailgunSeedListIntegrationTest.java)



## Testing

*WARNING* - running the tests will cost you money!

To run the tests, various environment variables must be set:
* `PRIVATE_API_KEY` To view your primary account API key in the Mailgun dashboard, click on Settings on the left-hand nav in the Mailgun dashboard and then API Keys and click on the eye icon next to [API_KEYS][api_keys].
* `MAIN_DOMAIN` is the domain name - this is a value registered in the Mailgun admin interface or using MailgunDomainsApi.class.
* `EMAIL_FROM` is the email address used in various sending tests.
* `EMAIL_TO` is the email address used in various sending tests.

Run tests, including integration tests:
```java
        mvn verify -Pintegration-test
```

Run tests, excluding integration tests:
```java
        mvn verify
```


## Contribute

Mailgun loves developers. You can be part of this project!

Feel free to ask anything, and contribute:

- Fork the project.
- Create a new branch.
- Implement your feature or bug fix.
- Add documentation for it.
- Add specs for your feature or bug fix.
- Commit and push your changes.
- Submit a pull request.

If you have suggestions on improving the guides, please submit an issue in our [Official API Documentation repo](https://github.com/mailgun/documentation).

