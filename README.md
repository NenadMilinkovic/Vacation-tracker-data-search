# Vacation-tracker-data-search service

## Working with docker

Clone also Vacation-tracker-data-import service(https://github.com/NenadMilinkovic/Vacation-tracker-data-import)

Run services with command from Vacation-tracker-data-import root folder:

    docker compose up

## lofin

* **URL:**

  `/api/login`

* **Method**

  `POST`

* **Body Params:**

  **Required:**

  `email=[string]`
  `password=[string]`

* **response**

  `token=[string]`

## Header

Every request must have Authorization header and value token from login endpoint

## Search vacation info

* **URL:**

  `/api/dataSearch/2019/vacationInfo`

* **Method**

  `GET`

* **Path Params:**

  **Required:**

  `year=[Int]`

## Import number of vacation days for year

* **URL:**

  `/api/dataSearch/usedVacationForPeriod`

* **Method**

  `GET`

* **Body Params:**

  **Required:**

  `startDate=[date(EEEE, MMMM dd, yyyy)]`
  `endDate=[date(EEEE, MMMM dd, yyyy)]`

## Add used vacation days

* **URL:**

  `/api/dataSearch/addUsedVacationDays`

* **Method**

  `POST`

* **Body Params:**

  **Required:**

  `startDate=[date(EEEE, MMMM dd, yyyy)]`
  `endDate=[date(EEEE, MMMM dd, yyyy)]`
  `spendDays=[Int]`
