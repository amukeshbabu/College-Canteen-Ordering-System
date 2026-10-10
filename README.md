# JeevaCan — Premium College Canteen Ordering System

> **A modern, static web edition of the JeevaCan College Canteen Ordering System.**  
> Crafted with semantic HTML5, modern CSS3 (with an 8-point design system), and vanilla JavaScript. Features full local state persistence, responsive layouts, and interactive software design pattern telemetry.

---

## 🌟 Highlights & Redesign Features

1. **Premium Aesthetic & Visual Hierarchy**
   - Warm off-white canvas (`#FAF9F6`) with deep charcoal typography (`#1C1917`).
   - Refined terracotta accent (`#D9532F`) inspired by Swiggy, Zomato, and Linear SaaS design systems.
   - Restrained, layered elevation shadows and subtle borders (`1px solid #EAE8E1`).
   - High-resolution food photography for authentic South Indian tiffins, meals, and beverages.

2. **8-Point Spacing & Layout Consistency**
   - Strictly aligns to an 8-point spacing scale (8px, 16px, 24px, 32px, 40px, 48px, 64px).
   - Centered container with a maximum width of 1240px and equal horizontal margins.
   - Equal card heights in grid rows, consistent image aspect ratios (`16:10` with `object-fit: cover`).
   - Zero horizontal overflow across all tested viewports (1440px desktop down to 320px mobile).

3. **Software Architecture Patterns in Vanilla JavaScript**
   - **Singleton Pattern**: Centralized `JeevaCanManager` maintaining a single persistent state in `localStorage` for orders, sequence numbering, and observers.
   - **Factory Pattern**: Food item catalog (`FoodFactory`) instantiating concrete items (`Idli`, `Dosa`, `FriedRice`, `VegMeals`, `Tea`, `Coffee`, etc.) with their respective prices and categories.
   - **Observer Pattern**: Automatic event dispatches notify both `CustomerObserver` and `KitchenObserver` whenever an order transitions across `PLACED → PREPARING → READY → COMPLETED`.
   - **Viva Automated Demo**: One-click interactive simulation to demonstrate the pattern transitions live for academic evaluation.

---

## 📁 Project Structure

```text
college-canteen-ordering-system/
├── pom.xml                        # Original Maven configuration (preserved)
├── src/                           # Original Java Spring Boot source (preserved)
└── static-site/                   # Complete standalone static web application
    ├── index.html                 # Dashboard, Hero, Live Metrics & Active Orders
    ├── menu.html                  # Food Catalog with Search & Category Filters
    ├── cart.html                  # Shopping Cart & Two-Column Checkout Layout
    ├── orders.html                # Active Orders Live Tracker & Customer Observer Feed
    ├── kitchen.html               # Kitchen Staff Operational Terminal & Live Transitions
    ├── history.html               # Complete Order Audit History & Revenue Metrics
    ├── css/
    │   └── style.css              # Centralized Design Tokens & Responsive CSS
    ├── js/
    │   ├── data.js                # Menu catalog, pattern metadata & seed orders
    │   └── app.js                 # Unified state store, Observers, Cart & UI engine
    ├── assets/
    │   ├── logo.svg               # Vector brand logo
    │   └── images/                # High-res curated food photography
    └── README.md                  # This documentation
```

---

## 🚀 How to Run Locally

Because the static edition uses relative paths and zero runtime dependencies, you can run it using any local HTTP server:

### Option 1: Python 3 (Built-in)
```bash
cd static-site
python3 -m http.server 8080
```
Open [http://localhost:8080](http://localhost:8080) in your browser.

### Option 2: Node.js (npx serve)
```bash
cd static-site
npx serve -l 8080 .
```

### Option 3: VS Code Live Server
Right-click on `static-site/index.html` and select **"Open with Live Server"**.

---

## 🌐 Deployment to Static Hosting

### 1. GitHub Pages
1. Push your repository to GitHub.
2. Go to **Settings** → **Pages**.
3. Under **Build and deployment**, choose **Deploy from a branch**.
4. Select your branch and set the folder to `/static-site` (or move the contents of `static-site/` to root or a `gh-pages` branch).
5. Save; your site will be live at `https://<username>.github.io/<repo-name>/`.

### 2. Netlify
1. Log in to [Netlify](https://www.netlify.com/).
2. Drag and drop the `static-site` folder into Netlify Drop, or link your Git repository.
3. If linking Git, set the **Publish directory** to `static-site`.
4. Deploy site instantly.

### 3. Vercel
1. Run `npx vercel` inside the `static-site` folder, or import the GitHub repository in the Vercel dashboard.
2. Set the **Root Directory** to `static-site`.

---

## 💾 Local Storage Persistence

- Orders, cart items, and observer event logs persist in `localStorage` under keys `jeevacan_cart_v2`, `jeevacan_orders_v2`, and `jeevacan_notifs_v2`.
- Initial seed orders provide an immediate, vibrant experience on first load.
- You can reset the demo state at any time by clicking **"🔄 Reset Demo Data"** in the footer of any page.
- *Note:* Because storage is browser-local, orders placed on one device will not be visible on other devices without a cloud database.

---

## 🎓 Academic Viva Demonstration Points

1. **Singleton Verification**:
   - `OrderManager` is a singleton ensuring order IDs increment sequentially without collisions (`#101`, `#102`, etc.).
2. **Factory Method Verification**:
   - Menu items are created via `FoodFactory` based on choice/id, maintaining clean separation between order management and item construction.
3. **Observer Event Flow**:
   - Kitchen staff transitioning an order on `kitchen.html` dispatches events that appear on `orders.html` for the customer.
   - The **"⚡ Run Viva Pattern Demo"** button automates an end-to-end lifecycle verification with time-staggered transitions.
