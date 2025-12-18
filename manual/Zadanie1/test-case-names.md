| Test Case Name                                 | Type (path) | Description                                                                      |
|------------------------------------------------|-------------|----------------------------------------------------------------------------------|
| test_login_with_valid_credentials              | Happy       | Login using valid username, password and server address.                         |
| test_login_with_invalid_credentials            | Negative    | Login attempt using invalid username and/or password. 				              |
| test_login_with_deleted_account_credentials    | Negative    | Login attempt using credentials of a deleted user account. 			          |
| test_login_with_empty_username_field           | Negative    | Login attempt with empty username field. 					                      |
| test_login_with_empty_password_field           | Negative    | Login attempt with empty password field. 					                      |
| test_login_with_empty_username_and_password    | Negative    | Login attempt with both username and password fields empty. 			          |
| test_login_with_empty_server_address           | Negative    | Login attempt with empty server address field. 				                  |
| test_login_with_incorrect_server_address       | Negative    | Login attempt using incorrect server address value. 				              |
| test_password_masked_by_default                | UI          | Verify password input is masked by default. 					                  |
| test_password_visibility_toggle                | UI          | Verify password visibility toggles correctly between masked and unmasked states. |
| test_login_button_state_for_invalid_input      | UI          | Verify login button state for incomplete or invalid input. 			          |
| test_username_input_text_not_overlapping       | UI          | Verify entered username text does not overlap with placeholder or UI elements.   |