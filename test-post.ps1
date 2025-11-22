# Test POST endpoints with PowerShell
Write-Host "Testing POST Endpoints..." -ForegroundColor Cyan
Write-Host ""

# Test 1: Create Categoria
Write-Host "1. Testing POST /api/categorias" -ForegroundColor Yellow
try {
    $body = @{ nombre = "Test Categoria $(Get-Date -Format 'HHmmss')" } | ConvertTo-Json
    $result = Invoke-RestMethod -Uri "http://localhost:8080/api/categorias" -Method POST -ContentType "application/json" -Body $body
    Write-Host "✅ SUCCESS" -ForegroundColor Green
    $result | ConvertTo-Json | Write-Host -ForegroundColor Gray
}
catch {
    Write-Host "❌ ERROR: $($_.Exception.Message)" -ForegroundColor Red
}
Write-Host ""

# Test 2: Create Marca
Write-Host "2. Testing POST /api/marcas" -ForegroundColor Yellow
try {
    $body = @{ 
        nombre      = "Test Marca $(Get-Date -Format 'HHmmss')"
        descripcion = "Descripcion de prueba"
    } | ConvertTo-Json
    $result = Invoke-RestMethod -Uri "http://localhost:8080/api/marcas" -Method POST -ContentType "application/json" -Body $body
    Write-Host "✅ SUCCESS" -ForegroundColor Green
    $result | ConvertTo-Json | Write-Host -ForegroundColor Gray
}
catch {
    Write-Host "❌ ERROR: $($_.Exception.Message)" -ForegroundColor Red
}
Write-Host ""

# Test 3: Create Unidad Medida
Write-Host "3. Testing POST /api/unidades-medida" -ForegroundColor Yellow
try {
    $body = @{ 
        nombre      = "Test Unidad $(Get-Date -Format 'HHmmss')"
        abreviatura = "TU"
    } | ConvertTo-Json
    $result = Invoke-RestMethod -Uri "http://localhost:8080/api/unidades-medida" -Method POST -ContentType "application/json" -Body $body
    Write-Host "✅ SUCCESS" -ForegroundColor Green
    $result | ConvertTo-Json | Write-Host -ForegroundColor Gray
}
catch {
    Write-Host "❌ ERROR: $($_.Exception.Message)" -ForegroundColor Red
}
Write-Host ""

# Test 4: Create Presentacion
Write-Host "4. Testing POST /api/presentaciones" -ForegroundColor Yellow
try {
    $body = @{ 
        nombre      = "Test Presentacion $(Get-Date -Format 'HHmmss')"
        descripcion = "Descripcion de prueba"
    } | ConvertTo-Json
    $result = Invoke-RestMethod -Uri "http://localhost:8080/api/presentaciones" -Method POST -ContentType "application/json" -Body $body
    Write-Host "✅ SUCCESS" -ForegroundColor Green
    $result | ConvertTo-Json | Write-Host -ForegroundColor Gray
}
catch {
    Write-Host "❌ ERROR: $($_.Exception.Message)" -ForegroundColor Red
}
Write-Host ""

# Test 5: Create Cliente
Write-Host "5. Testing POST /api/clientes" -ForegroundColor Yellow
try {
    $body = @{ 
        nombre       = "Test"
        apellidos    = "Cliente"
        nroDocumento = "$(Get-Random -Minimum 10000000 -Maximum 99999999)"
        direccion    = "Av. Test 123"
        celular      = "999999999"
        estado       = $true
    } | ConvertTo-Json
    $result = Invoke-RestMethod -Uri "http://localhost:8080/api/clientes" -Method POST -ContentType "application/json" -Body $body
    Write-Host "✅ SUCCESS" -ForegroundColor Green
    $result | ConvertTo-Json | Write-Host -ForegroundColor Gray
}
catch {
    Write-Host "❌ ERROR: $($_.Exception.Message)" -ForegroundColor Red
}
Write-Host ""

Write-Host "All POST tests completed!" -ForegroundColor Cyan
