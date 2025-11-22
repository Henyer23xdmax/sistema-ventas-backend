# Script para probar todos los endpoints de la API
# Base URL
$baseUrl = "http://localhost:8080/api"

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  PROBANDO ENDPOINTS DE LA API REST" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Función para probar un endpoint GET
function Test-GetEndpoint {
    param (
        [string]$endpoint,
        [string]$nombre
    )
    
    Write-Host "Probando: $nombre" -ForegroundColor Yellow
    Write-Host "GET $endpoint" -ForegroundColor Gray
    
    try {
        $response = Invoke-RestMethod -Uri "$baseUrl/$endpoint" -Method GET -ErrorAction Stop
        Write-Host "✅ SUCCESS - Registros encontrados: $($response.Count)" -ForegroundColor Green
        if ($response.Count -gt 0) {
            Write-Host "   Primer registro:" -ForegroundColor Gray
            $response[0] | ConvertTo-Json -Depth 2 | Write-Host -ForegroundColor DarkGray
        }
    }
    catch {
        Write-Host "❌ ERROR: $($_.Exception.Message)" -ForegroundColor Red
    }
    Write-Host ""
}

# Función para probar un endpoint POST
function Test-PostEndpoint {
    param (
        [string]$endpoint,
        [string]$nombre,
        [hashtable]$data
    )
    
    Write-Host "Probando: $nombre (CREATE)" -ForegroundColor Yellow
    Write-Host "POST $endpoint" -ForegroundColor Gray
    
    try {
        $json = $data | ConvertTo-Json -Depth 3
        Write-Host "   Datos enviados:" -ForegroundColor Gray
        Write-Host $json -ForegroundColor DarkGray
        
        $response = Invoke-RestMethod -Uri "$baseUrl/$endpoint" -Method POST -ContentType "application/json" -Body $json -ErrorAction Stop
        Write-Host "✅ SUCCESS - Registro creado" -ForegroundColor Green
        $response | ConvertTo-Json -Depth 2 | Write-Host -ForegroundColor DarkGray
    }
    catch {
        Write-Host "❌ ERROR: $($_.Exception.Message)" -ForegroundColor Red
    }
    Write-Host ""
}

Write-Host "=== PROBANDO ENDPOINTS GET ===" -ForegroundColor Cyan
Write-Host ""

# Probar todos los endpoints GET
Test-GetEndpoint "categorias" "Categorías"
Test-GetEndpoint "marcas" "Marcas"
Test-GetEndpoint "unidades-medida" "Unidades de Medida"
Test-GetEndpoint "presentaciones" "Presentaciones"
Test-GetEndpoint "clientes" "Clientes"
Test-GetEndpoint "productos" "Productos"
Test-GetEndpoint "lotes" "Lotes"
Test-GetEndpoint "comprobantes" "Comprobantes"

Write-Host "=== PROBANDO ENDPOINTS POST ===" -ForegroundColor Cyan
Write-Host ""

# Probar creación de categoría
Test-PostEndpoint "categorias" "Crear Categoría" @{
    nombre = "Categoría de Prueba $(Get-Date -Format 'HHmmss')"
}

# Probar creación de marca
Test-PostEndpoint "marcas" "Crear Marca" @{
    nombre = "Marca de Prueba $(Get-Date -Format 'HHmmss')"
    descripcion = "Descripción de prueba"
}

# Probar creación de unidad de medida
Test-PostEndpoint "unidades-medida" "Crear Unidad de Medida" @{
    nombre = "Unidad de Prueba $(Get-Date -Format 'HHmmss')"
    abreviatura = "UP"
}

# Probar creación de presentación
Test-PostEndpoint "presentaciones" "Crear Presentación" @{
    nombre = "Presentación de Prueba $(Get-Date -Format 'HHmmss')"
    descripcion = "Descripción de prueba"
}

# Probar creación de cliente
Test-PostEndpoint "clientes" "Crear Cliente" @{
    nombre = "Cliente"
    apellidos = "De Prueba"
    nroDocumento = "$(Get-Random -Minimum 10000000 -Maximum 99999999)"
    direccion = "Av. Prueba 123"
    celular = "987654321"
    estado = $true
}

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  PRUEBAS COMPLETADAS" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
