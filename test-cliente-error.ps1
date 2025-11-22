# Capture detailed error from Cliente POST
Write-Host "Testing Cliente POST with detailed error capture..." -ForegroundColor Cyan

$body = @{
    nombre       = "TestCliente"
    apellidos    = "Apellido Test"
    nroDocumento = "55555555"
    direccion    = "Calle Test 123"
    celular      = "987654321"
    estado       = $true
} | ConvertTo-Json

Write-Host "Request body:" -ForegroundColor Yellow
Write-Host $body -ForegroundColor Gray
Write-Host ""

try {
    $response = Invoke-RestMethod -Uri "http://localhost:8080/api/clientes" -Method POST -ContentType "application/json" -Body $body
    Write-Host "✅ SUCCESS!" -ForegroundColor Green
    $response | ConvertTo-Json | Write-Host -ForegroundColor Gray
}
catch {
    Write-Host "❌ ERROR occurred" -ForegroundColor Red
    Write-Host "Status Code: $($_.Exception.Response.StatusCode.value__)" -ForegroundColor Yellow
    
    if ($_.ErrorDetails.Message) {
        Write-Host "Error Details:" -ForegroundColor Yellow
        $_.ErrorDetails.Message | Write-Host -ForegroundColor Red
    }
    
    Write-Host "`nFull Exception:" -ForegroundColor Yellow
    $_.Exception.Message | Write-Host -ForegroundColor Red
}
