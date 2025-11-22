# Final test with full error capture
$uri = "http://localhost:8080/api/clientes"
$body = @{
    nombre       = "Test"
    apellidos    = "Final"
    nroDocumento = "88888888"
    direccion    = "Test"
    celular      = "999999999"
    estado       = $true
} | ConvertTo-Json

Write-Host "POST $uri" -ForegroundColor Cyan
Write-Host $body -ForegroundColor Gray
Write-Host ""

$response = Invoke-WebRequest -Uri $uri -Method POST -ContentType "application/json" -Body $body -SkipHttpErrorCheck

Write-Host "Status Code: $($response.StatusCode)" -ForegroundColor $(if ($response.StatusCode -eq 201) { "Green" } else { "Red" })
Write-Host "Response:" -ForegroundColor Yellow
$response.Content | ConvertFrom-Json | ConvertTo-Json -Depth 10
