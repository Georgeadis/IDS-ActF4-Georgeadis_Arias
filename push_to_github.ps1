<#
Script: push_to_github.ps1
Descripción: Automatiza init (si hace falta), add, commit, remote and push al repo remoto.
Uso recomendado: ejecutar desde la raíz del proyecto (doble clic o PowerShell).
Parámetros opcionales:
 -RemoteUrl  : URL del repo remoto (por defecto https://github.com/Georgeadis/IDS-ActF4-Georgeadis_Arias.git)
 -Branch     : Rama a pushear (por defecto main)
 -Message    : Mensaje de commit (por defecto 'Actualización desde script')
 -Token      : (OPCIONAL) Token de GitHub; si se provee el script usará un remote temporal con el token embebido.

AVISO: Incluir un token en la URL deja el token en `.git/config`. Es más seguro usar el Git Credential Manager o variables de entorno.
#>

param(
    [string]$RemoteUrl = 'https://github.com/Georgeadis/IDS-ActF4-Georgeadis_Arias.git',
    [string]$Branch = 'main',
    [string]$Message = 'Actualización desde script',
    [string]$Token
)

Set-StrictMode -Version Latest
Push-Location -LiteralPath $PSScriptRoot
try {
    if (-not (Test-Path .git)) {
        Write-Host "No hay repo Git. Inicializando .git..."
        git init
    }

    Write-Host "Configurando remote 'origin'..."
    git remote remove origin 2>$null | Out-Null

    if ($Token -and $Token.Trim() -ne '') {
        # Construir URL con token (nota: esto se guardará en .git/config)
        $escaped = $Token -replace '@','%40'
        $pushUrl = $RemoteUrl -replace '^https://', "https://$escaped@"
    } else {
        $pushUrl = $RemoteUrl
    }

    git remote add origin $pushUrl

    Write-Host "Agregando cambios..."
    git add -A

    $status = git status --porcelain
    if ($status) {
        git commit -m $Message
    } else {
        Write-Host "No hay cambios para commitear."
    }

    git branch -M $Branch
    Write-Host "Pusheando a $Branch en $RemoteUrl ..."
    git push -u origin $Branch
    Write-Host "Push finalizado."
}
catch {
    Write-Error "Error: $_"
}
finally {
    Pop-Location
}
