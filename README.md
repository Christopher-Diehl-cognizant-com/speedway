
Introduction
============

Speedway Trials API is an open API service that can be used by racecar
enthusiasts, participants and organizers. With our application, anyone
can browse historical race data and get all the exciting details about
cars, drivers and race outcomes. It can also be used to record new
races, add new cars and add new drivers.

The intent of the service is to serve data that allows a client to:

-   Browse the racecars to get details about them.
-   Browse the drivers to get details about their records and cars.
-   Browse races to get details about the stats, participants and
    outcomes.

Make frequent commits to github so your instructors can support your
work. Commits should always be small. For example, commiting code after
making each test pass or after refactoring a section of code. All tests
in the repo should be green and the app should start without error if
your instructor downloads it.

Setup
-----

Here is a checklist of things you should do in order to ensure success:

1.  Create a **Gradle** project from
    [start.spring.io](https://start.spring.io/)
    -   Ensure you have **ALL** dependencies you will need.

2.  Extract project and make initial commit
3.  Create GitHub repo
    -   Add all team members to repo
    -   Add Wes `whediger`{tabindex="0"} and David
        `davidgrieser`{tabindex="0"} to your repo
    -   Setup remote for project and push intial commit

4.  Start project on GitHub to track work to do
5.  Create issues for the following
    -   README setup
    -   Docker setup
        -   Dockerfile
        -   Docker Spring Profile
        -   docker-compose.yml (optional)
    -   Heroku setup
        -   Need Configuration In App
        -   Need App on Heroku to deploy to
    -   GitHub Actions
        -   Gradle Build/Test
        -   Heroku Deploy
    -   Racecar Stories/Issues
        -   These can be as large or small in scope that you want to be
            effective.
        -   GET and POST are priority.
    -   Driver Stories/Issues
        -   These can be as large or small in scope that you want to be
            effective.
        -   GET and POST are priority.

6.  Divide and Conquer (aka Pair off and do work)
7.  ????
8.  Profit

Requirements
------------

1.  Must have API specs for all endpoints, use Spring REST Docs.
2.  Must have integration tests and unit tests.
3.  As always TDD is expected.
4.  Utilize Spring Profiles and Docker enable multiple run environments.
5.  A local application running in Docker that uses Docker Postgres as
    DB. Provide instructions on how to run this.
6.  A deployed application on Heroku that uses Heroku Postgres as DB.
7.  App should be deployed to Heroku via GitHub Actions
8.  App should be restartable and still persist all data on Heroku.
    -   aka: make sure `ddl-auto`{tabindex="0"} is properly configured.

### Not Required

1.  Spring Security
2.  Code Coverage

Github Actions
--------------

Use Github Actions to create a deployment pipeline for your project. The
pipeline should at least:

-   Test the app
-   Build the image
-   Deploy the service (manually or automatically)

Use the [Github Actions
guide](https://learn-2.galvanize.com/cohorts/2560/blocks/1164/content_files/units/06_CICD_Intro/04-github_actions.md)

to aid you.

API Documentation
-----------------

Your application should include full Spring REST documentation for:

-   Endpoints
-   Request details
-   Response details
-   Error responses

### API Responses

All responses must return the following information:

-   The status code of the response
-   The status message for the response
-   The data requested: lists must be an array and individual items must
    be an object/hash

Example successful response for `/api/v1/racecars`{tabindex="0"} might
loo like this:

    {
        "status": "OK",
        "status_code": 200,
        "data": [
            {
                "id": 43,
                "nickname": "The Condor",
                "model": "Corvette",
                "year": "2019",
                "owner": {...},
                "status": "AVAILABLE",
                "top_speed": 189
            },
            {
                "id": 44,
                "nickname": "Blue Fire",
                "model": "Ferrari",
                "year": "2017",
                "owner": {...},
                "status": "UNAVAILABLE",
                "top_speed": 223
            }
        ]
    }

Example error response:

    {
        "status": "Bad request.",
        "status_code": 400,
        "data": ["A list", "of errors", "that lead to this outcome"]
    }

Resources
---------

Below are some details about the resources the service provides. Use
this as a starting point. Don't treat these as your final objects.
You're expected to improvise and come up with smart solutions.

#### Racecars... {#racecars}

-   have a nickname and model
-   belong to a `Driver`{tabindex="0"}.
-   have a status: available, unavailable
-   have a top speed in miles per hour (mph) by default, which can be
    converted to kilometers per hour (kph)
-   have a type which can be one of the following: compact, mid-size,
    full, sports and trucks.

Cars come in the following models:
`Alpine, Ferrari, Maserati, Porsche, Nissan and Jaguar`{tabindex="0"}.

An example of a car object:

    {
        "id": 43,
        "nickname": "The Condor",
        "model": "Corvette",
        "year": "2019",
        "owner": 27,
        "status": "AVAILABLE",
        "top_speed": 189
    }

Clients can add new cars and view cars. Cars cannot be updated or
deleted.

#### Drivers... {#drivers}

-   have a first name, last name, nickname, age and birthdate
-   have many `Car`{tabindex="0"}s
-   have wins and losses (cannot be edited; read only)

An example of a driver object:

    {
        "id": 332,
        "first_name": "Maria",
        "last_name": "Lopez",
        "age": 22,
        "nickname": "The Blaze",
        "cars": [...],
        "wins": 4,
        "losses": 1
    }

Clients can add new drivers and view drivers. Drivers cannot be deleted
and their cars can only be updated by adding a new one (you cannot
remove cars from a driver).

## Docker commands
```docker build -t speedway:dev .```
# Setup
## Postgres
```
docker network create --driver bridge speedway-net
docker run --name speedway_pg --network speedway-net -e POSTGRES_PASSWORD=speedway -e POSTGRES_DB=speedway_db -d postgres
```
### Run
```
docker run --name speedway1 --network speedway-net -e SPRING_PROFILES_ACTIVE=docker -e PORT=8080 -p 9000:8080 -d speedway:dev
```

## Heroku App
Docs Link: https://speedwaytrials.herokuapp.com/docs/index.html

Racecars Link: https://speedwaytrials.herokuapp.com/speedway/racecars

Drivers Link: https://speedwaytrials.herokuapp.com/speedway/drivers
