# Script para compilar y ejecutar el proyecto desde PowerShell
Set-StrictMode -Version Latest
Push-Location -LiteralPath "$PSScriptRoot"
try {
    $src = Join-Path $PSScriptRoot 'src'
    $bin = Join-Path $PSScriptRoot 'bin'
    if (-Not (Test-Path $bin)) { New-Item -ItemType Directory -Path $bin | Out-Null }
    else {
        # limpiar clases antiguas para evitar ejecutar versiones obsoletas
        Get-ChildItem -Path $bin -Filter *.class -ErrorAction SilentlyContinue | Remove-Item -Force -ErrorAction SilentlyContinue
    }
    Write-Host "Compilando..."
    Set-Location -LiteralPath $src
    & javac -d "..\bin" *.java
    if ($LASTEXITCODE -ne 0) {
        Write-Error "Compilación fallida. Revise los errores arriba."
        exit $LASTEXITCODE
    }
    Write-Host "Ejecución de Main..."
    & java -cp "..\bin" Main
}
finally {
    Pop-Location
}
