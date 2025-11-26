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

While doing testing on frontend, encountered issues getting node to run, had to use {} and export all of the declarations/definitons marked with extern, add transform: {} to the jest.config.js, did testing for validateGraphData, buildGraph and getNearbyCities
