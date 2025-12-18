# 2 — Full Test Case

## Test Case ID
TC_LOGIN_001

## Title
Login with valid credentials (Happy path)

## Related scenario (from Task 1)
test_login_with_valid_credentials

## Short description
Login using valid username, password and server address.

## Type (path)
Happy path/Manual

## Priority
High

## Preconditions
- Mobile application is installed and can be opened.
- Test user account exists and is active.
- Device has network connectivity.
- User is currently logged out (fresh install or logged out state).
- (Assumption) Valid server address value is available for the environment under test.

## Test Data
- Username: <VALID_USERNAME>
- Password: <VALID_PASSWORD>
- Server address: <VALID_SERVER_ADDRESS>

## Steps
| Step | Action                                                          | Expected Result                                                                                                 |
|------|-----------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------|
| 1    | Open the mobile application.                                    | Login screen is displayed with inputs: Username, Password, Server address, and Login button.                    |
| 2    | Enter `<VALID\_USERNAME>` into the Username field.              | Username is accepted and visible in the field (no UI overlap).                                                  |
| 3    | Enter `<VALID\_PASSWORD>` into the Password field.              | Password input is accepted and masked (dots/bullets) by default.                                                |
| 4    | Enter `<VALID\_SERVER\_ADDRESS>` into the Server address field. | Server address is accepted and visible in the field.                                                            |
| 5    | Tap the Login button.                                           | Login request is triggered and the app proceeds to the post-login state (main/home screen) without error.       |
| 6    | Verify the user is logged in.                                   | The user remains authenticated (user can access the first screen after login; login screen is no longer shown). |

## Postconditions
- User is logged in (login screen is no longer shown).

## Notes / Assumptions
- Exact screen name after login and exact success indicators are not provided in the task, so verification is described in a neutral way.
- Field labels and UI elements are based on the provided screenshot.