# TwitterTest

DESCRIPTION:

The task was completed using my own framework (junit + extent reports + selenium).
You can find test account data in test-data.csv.
In config.properties you can choose parameters for the project (browser name, start url, locale for Logging etc.).
Пenerated report you can find in the target folder.
This application works only for the English version of the site.

SCENARIO:

1. Navigate to https://twitter.com/
2. Log in with a test user
3. Create new tweet with random text
4. Make sure that the tweet from expected user was created
5. Make sure that the tweet with expected text was created
6. Delete tweet
7. Log out 
8. Make sure that the user was logged out
