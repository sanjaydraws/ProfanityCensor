# Profanity Censor API

**Profanity Filter API** is a simple HTTP API built using **Ktor** that allows you to filter and censor profane words from input text. It utilizes a customizable list of profane words, and the response includes the original text, the censored version, and a flag indicating whether any profanity was detected.

## Features

- Censors profane words in the input text.
- Returns the original text, the censored text, and a profanity detection flag.
- Easy integration with other applications.
- Supports JSON input and output.
- Requires an API key for secure access.

### Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/yourusername/ProfanityFilter.git
    cd ProfanityFilter
    ```

2. Build the project using Gradle:
    ```bash
    ./gradlew build
    ```

3. Run the application:
    ```bash
    ./gradlew run
    ```

4. The server will start on `http://localhost:8080`.

## API Usage

### Endpoint

`POST /profanityfilter`

### Request

You need to send a JSON payload containing the text to be filtered. The API key must be included in the request headers.

#### Request Headers

- `X-Api-Key`: 1febc51b-1493-49f6-a85b-15f684269321.

#### Request Body

The request body should be a JSON object containing the text you want to filter.

**Example Request Body:**
```json
{
   "text": "Why the hell would you do that?"
}
```

### Example Response
```json
{
   "original": "Why the hell would you do that?",
   "censored": "Why the **** would you do that?",
   "has_profanity": true
}
```


