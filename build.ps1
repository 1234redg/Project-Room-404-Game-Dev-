# Build script for Project Room 404 Game Dev
# Usage: .\build.ps1 [clean|build|rebuild]

param(
    [string]$action = "build"
)

$srcDir = ".\Game Development\src"
$binDir = ".\bin"

function Clean {
    Write-Host "🧹 Cleaning compiled files..." -ForegroundColor Yellow
    if (Test-Path $binDir) {
        Remove-Item -Path $binDir -Recurse -Force -Verbose
        Write-Host "✓ Clean complete" -ForegroundColor Green
    } else {
        Write-Host "✓ Nothing to clean" -ForegroundColor Green
    }
}

function Build {
    Write-Host "🔨 Building project..." -ForegroundColor Yellow
    
    # Create bin directory if it doesn't exist
    if (-not (Test-Path $binDir)) {
        New-Item -ItemType Directory -Path $binDir -Force | Out-Null
        Write-Host "✓ Created bin directory" -ForegroundColor Green
    }
    
    # Find all .java files
    $javaFiles = @(Get-ChildItem -Path $srcDir -Recurse -Filter "*.java" -ErrorAction SilentlyContinue).FullName
    
    if ($javaFiles.Count -eq 0) {
        Write-Host "❌ No Java files found in $srcDir" -ForegroundColor Red
        return
    }
    
    Write-Host "Compiling $($javaFiles.Count) Java files..." -ForegroundColor Cyan
    
    # Compile with -d flag to output to bin directory
    javac -d $binDir -sourcepath $srcDir @javaFiles 2>&1
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host "✓ Build successful! Classes compiled to: $binDir" -ForegroundColor Green
    } else {
        Write-Host "❌ Build failed!" -ForegroundColor Red
        exit 1
    }
}

function Rebuild {
    Clean
    Build
}

# Execute action
switch ($action.ToLower()) {
    "clean" { Clean }
    "build" { Build }
    "rebuild" { Rebuild }
    default {
        Write-Host "Usage: .\build.ps1 [clean|build|rebuild]" -ForegroundColor Cyan
        Write-Host ""
        Write-Host "Examples:" -ForegroundColor Cyan
        Write-Host "  .\build.ps1 build           # Compile to bin/" -ForegroundColor Gray
        Write-Host "  .\build.ps1 clean           # Remove all compiled files" -ForegroundColor Gray
        Write-Host "  .\build.ps1 rebuild         # Clean and rebuild" -ForegroundColor Gray
    }
}
