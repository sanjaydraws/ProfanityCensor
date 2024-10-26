# Profanity Filter API

**Profanity Filter API** is a simple HTTP API built using **Ktor** that allows you to filter and censor profane words from input text. It utilizes a customizable list of profane words, and the response includes the original text, the censored version, and a flag indicating whether any profanity was detected.

## Features

## Features

- Censors profane words in the input text.
- Returns the original text, the censored text, and a profanity detection flag.
- Provides statistics on the number of profane words detected, including counts for each specific word.
- Supports customizable censorship with options for different replacement characters or strings.
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

The request body should be a JSON object containing the following parameters:

- `text` (string, required): The text that you want to filter for profane words.
- `replacement` (string, optional): The character(s) to use for censoring profane words. Defaults to `*` if not provided.

**Example Request Body:**
```json
{
   "text": "Why the hell would you do that?",
   "replacement": "*"
}
```

### Example Response
The response will be a JSON object containing the following fields:

- `original (string)` : The original input text.
- `censored (string)` : The censored version of the text with profane words replaced.
- `has_profanity (boolean)` : A flag indicating whether any profanity was detected.
- `statistics (object)`: An optional field that provides statistics about the profanity detected.
- `totalProfaneWords (integer)`: The total number of profane words found in the text.
- `profaneWordCounts (object)`: A map of profane words and their counts.

```json
{
   "original": "Why the hell would you do that?",
   "censored": "Why the **** would you do that?",
   "has_profanity": true,
   "statistics": {
      "totalProfaneWords": 1,
      "profaneWordCounts": {
         "hell": 1
      }
   }
}

```


