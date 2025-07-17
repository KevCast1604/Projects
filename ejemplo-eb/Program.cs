using ejemplo_eb.Inventories.Application.Internal.CommandServices;
using ejemplo_eb.Inventories.Application.Internal.QueryServices;
using ejemplo_eb.Inventories.Domain.Repository;
using ejemplo_eb.Inventories.Domain.Services;
using ejemplo_eb.Inventories.Infrastructure;
using ejemplo_eb.Manufacturing.Application.Internal.CommandServices;
using ejemplo_eb.Manufacturing.Application.Internal.QueryServices;
using ejemplo_eb.Manufacturing.Domain.Repository;
using ejemplo_eb.Manufacturing.Domain.Services;
using ejemplo_eb.Manufacturing.Interfaces.ACL;
using ejemplo_eb.Shared.Domain.Configuration;
using ejemplo_eb.Shared.Infrastructure.Persistence.EFC.Configuration;
using Microsoft.EntityFrameworkCore;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddScoped<IInventoryCommandService, CreateProductCommandService>();
builder.Services.AddScoped<IInventoryQueryService, GetProductByIdQueryService>();
builder.Services.AddScoped<IInventoryRepository, InventoryRepository>();
builder.Services.AddScoped<IBillOfMaterialsItemCommandService, CreateBillOfMaterialsItemCommandService>();
builder.Services.AddScoped<IBillOfMaterialsItemQueryService, BillOfMaterialsItemQueryService>();
builder.Services.AddScoped<IBillOfMaterialsItemRepository, BillOfMaterialsItemRepository>();
builder.Services.AddScoped<IInventoryAclService, InventoryAclService>();


// Add services to the container.

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

builder.Services.AddDbContext<AppDbContext>(options =>
    options.UseMySql(
        builder.Configuration.GetConnectionString("DefaultConnection"),
        ServerVersion.AutoDetect(builder.Configuration.GetConnectionString("DefaultConnection"))
    )
);

builder.Services.Configure<CapacityThresholdsConfig>(
    builder.Configuration.GetSection("CapacityThresholds"));

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Run();
