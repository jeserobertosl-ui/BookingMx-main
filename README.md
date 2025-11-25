# BookingMx

Minimal vanilla JS + Spring Boot project to practice unit tests.

## Run backend
```bash
cd backend
mvn spring-boot:run
```

## Run frontend
```bash
cd frontend
npm i
npm run serve
# http://localhost:5173
```
# BookingMx

The main problem was with the update function, I didn't realize that I was making the necessary changes after calling the Test, so the test was evaluating the wrong data
