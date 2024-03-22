# CGI Summer Internship Take-Home Assignment (Cinema)

## Technologies Used

- **Frontend**: Vue.js(3.4.21) with Tailwind CSS
- **Backend**: Spring Boot(3.2.3) with Java 21(latest LTS)


## Usage

### Running the Backend

1. Set up the database using Docker Compose:

    ```bash
    cd backend
    docker-compose up -d
    ```

2. Build the backend application:

    ```bash
    cd backend
    mvn clean install
    ```

3. Start the Spring Boot application:

    ```bash
    mvn spring-boot:run
    ```

### Running the Frontend

1. Navigate to the `frontend` directory:

    ```bash
    cd frontend
    ```

2. Install dependencies:

    ```bash
    npm install
    ```

3. Run the frontend application:

    ```bash
    npm run serve
    ```

   The frontend application will be accessible at `http://localhost:5713`.

## Things that took long time

1. JPA ManyToMany relationships (List has to be used instead of Set)
2. Seating algorithm
3. Database initialization


## Things not accomplished

1. Validation, not in back or frondend
2. Snackbars, without falidation or api error handling there is no need for snackbars
3. Authorization/authentication
4. Session search. Partly because current design wouldnt have really worked with session search.
5. Movie reccomendations.
