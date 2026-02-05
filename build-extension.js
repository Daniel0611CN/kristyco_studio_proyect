const fs = require('fs');
const path = require('path');

// Define source and destination paths
const sourceDir = path.join(__dirname, 'extension');
const destDir = path.join(__dirname, 'dist', 'extension', 'browser');

// Function to recursively copy directory
function copyDirectory(src, dest) {
    // Create destination directory if it doesn't exist
    if (!fs.existsSync(dest)) {
        fs.mkdirSync(dest, { recursive: true });
    }

    // Read all files and directories in source
    const entries = fs.readdirSync(src, { withFileTypes: true });

    for (const entry of entries) {
        const srcPath = path.join(src, entry.name);
        const destPath = path.join(dest, entry.name);

        if (entry.isDirectory()) {
            // Recursively copy subdirectories
            copyDirectory(srcPath, destPath);
        } else {
            // Copy files
            fs.copyFileSync(srcPath, destPath);
            console.log(`Copied: ${entry.name}`);
        }
    }
}

// Main build function
function buildExtension() {
    console.log('Building browser extension...');
    console.log(`Source: ${sourceDir}`);
    console.log(`Destination: ${destDir}`);
    
    try {
        // Clean destination directory before building
        if (fs.existsSync(destDir)) {
            fs.rmSync(destDir, { recursive: true, force: true });
            console.log('Cleaned previous build');
        }
        
        // Copy extension files
        copyDirectory(sourceDir, destDir);
        
        console.log('\n✓ Extension built successfully!');
        console.log(`Output location: ${destDir}`);
    } catch (error) {
        console.error('Error building extension:', error);
        process.exit(1);
    }
}

// Run the build
buildExtension();
