# Kristyco Studio Browser Extension

This directory contains the browser extension for Kristyco Studio.

## Building the Extension

To build the browser extension, run:

```bash
npm run build:ext
```

This will compile the extension and output it to `dist/extension/browser/`.

## Loading the Extension in Chrome/Edge

1. Build the extension using the command above
2. Open Chrome/Edge and navigate to `chrome://extensions/` (or `edge://extensions/`)
3. Enable "Developer mode" in the top right
4. Click "Load unpacked"
5. Select the `dist/extension/browser/` directory

## Extension Structure

- `extension/manifest.json` - Extension manifest (Chrome Extension Manifest V3)
- `extension/popup.html` - Popup UI HTML
- `extension/popup.css` - Popup UI styles
- `extension/popup.js` - Popup UI JavaScript
- `extension/icons/` - Extension icons (16x16, 48x48, 128x128)

## Requirements

- Node.js >= 14.14.0
