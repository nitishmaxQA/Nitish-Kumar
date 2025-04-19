# Monefy Exploratory Testing Charter

---

## Charter 1: Explore Walkthrough Screens

**Scope/Focus:**
- Tapping the CTA ("Next", "Continue", etc.) should always take the user to the correct next screen.
- No swipe gesture required — swiping should ideally do nothing (or show no error).
- CTAs are visible, clickable, and properly labeled (no missing buttons).
- Content (text, icons, descriptions) on each screen loads correctly.
- Progression is smooth — no lag, delay, or crash when tapping CTA.
- Final CTA leads to "Claim your Welcome Offer" screen properly.

**Findings:**  
- Welcome offer screen text changes after killing and relaunching the app.

---

## Charter 2: Explore and Validate the "Claim Your Welcome Offer" Screen

**Scope/Focus:**
- Welcome Offer details are displayed correctly (offer text, expiry timing, terms link).
- T&C and other links work as expected.
- Claim offer with and without Google account logged into the device.
- If offer is already claimed, proper message should come; else take user to payment options.
- Check RESTORE CTA functionality; show error message if no subscription existed.
- Explore Offer ends countdown.
- Check app behavior if user closes app midway — Welcome offer page should reload.

**Findings:**
- **Bug 1:** If Google account is not logged in, infinite loader is seen on OFFER screen.  
  ➔ Suggestion: Show message to log in with Google account to make a purchase.

- **Bug 2:** Multiple taps are allowed on RESTORE CTA, message shown with delay.  
  ➔ Suggestion: Show loader until purchase history response is received; restrict user from tapping multiple times.

- **Bug 3:** Privacy and T&C links have no functionality if Chrome and other browsers are disabled.  
  ➔ Suggestion: Either ask user to install a browser, or implement an in-app browser.

- **Bug 4:** User can extend "offer end time" by manually increasing device time.  
  

---

## Charter 3: Explore App Usage Tutorials for Accuracy, Navigation, and Guidance Quality

**Scope/Focus:**
- Tutorials highlight or point to the correct UI elements, features, or actions.
- Ensure descriptions match what's shown on the screen.
- Verify that the tutorial guidance is clear, helpful, and contextually accurate.

**Findings:**
- Minor: While tutorials highlight features, user can still interact with the background (e.g., continue adding expenses).
  - **Suggestion:** Disable background interactions during the tutorial.
  
- Rare issue: The pointer highlight remains stuck on the screen even after completing the tutorial.
  - **Suggestion:** Ensure pointer disappears after tutorial completion; user should not need to kill the app.

---

## Charter 4: Test the Functionality of Adding Expenses and Income

**Scope/Focus:**
- Verify that users can add expenses and income under correct categories.
- Check if the pie chart accurately reflects added expenses and income.
- Confirm users can edit existing entries correctly.
- Test the calculator feature while adding amounts.
- Verify correct date is shown while adding amount.
- Ensure real-time updates: changes immediately reflect in balance and pie chart.

**Findings:**
- "Choose Category" list shows 15 options but only 14 appear on the main page.
- Some pie chart slices do not have connecting lines to categories.
- Home page shows only icons — category names missing; can cause confusion.
- Expense and income CTAs switch to '+' and '-' signs after a few actions.

---

## Charter 5: Explore App Hamburger Menu Items

**Scope/Focus:**
- User can simplify expense view by Day/Week/Month intervals or custom date range.
- Check:
  - Daily view groups expenses per day.
  - Weekly view groups expenses per week.
  - Monthly view groups expenses per month/year.
  - Custom range correctly filters expenses.
- Ensure switching between intervals doesn't reset previous selections.
- Check pie chart and balance values update based on selected interval.
- Future date range should show "No expenses found" message.
- Switching views should be fast, with no crash or lag.

**Findings:**
- No issue observed

---

# Prioritization of Charters with Reason

| Charter | Priority | Reason |
|:---|:---|:---|
| **Charter 2: Explore and Validate "Claim Your Welcome Offer"** | **High** | Bugs affect subscription process, user trust, and first impression. Infinite loader, multiple taps, and expiry extension are critical. |
| **Charter 3: Explore App Usage Tutorials** | **Medium** | Impacts onboarding smoothness and UX quality. Bugs like pointer getting stuck are annoying but not app breaking. |
| **Charter 4: Adding Expenses and Income** | **High** | Core functionality of the app. Errors here (like wrong categories, pie chart issues) directly impact user experience and financial tracking. |
| **Charter 1: Explore Walkthrough Screens** | **Low** | Walkthrough mainly impacts first impression. Minor issues, no crashes or severe functional impact found. |
| **Charter 5: Explore Hamburger Menu Items** | **Medium** | Impacts usability and filtering. No critical bugs found, but it’s important for daily users to view transactions efficiently. |

---

# Risks to Mitigate

| Type of Risk | Details |
|:---|:---|
| **Data Integrity Risk** | Incorrect balance, wrong category mapping, pie chart mismatch can lead to financial misreporting. |
| **Data Loss Risk** | User loses expense/income data due to app crash, reinstall, device change, or no cloud backup. |
| **Revenue Loss Risk** | Offer screen bugs (infinite loader, restore issues) can cause users not to purchase subscriptions. |
| **Security and Privacy Risk** | External links (Privacy Policy, T&C) fail if browsers are disabled; legal compliance risk. |
| **Usability Risk** | Pointer stuck, poor tutorial control, confusing UX during onboarding frustrates users. |
| **Performance Risk** | App slowdowns, lags while switching views (Day/Week/Month) or restoring purchase impact user satisfaction. |
| **Manipulation Risk** | Users can extend offers by changing device time unless server-side validation is done. |
| **Data Security & Privacy** | Sensitive financial data could be leaked or hacked if strong encryption, authentication, or app lock isn't implemented. |

---
