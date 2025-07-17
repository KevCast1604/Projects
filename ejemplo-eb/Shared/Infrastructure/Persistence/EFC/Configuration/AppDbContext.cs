using ejemplo_eb.Manufacturing.Domain.Model.Aggregates;
using ejemplo_eb.Shared.Infrastructure.Persistence.EFC.Configuration.Extensions;
using EjemploEb.Inventories.Domain.Model.Aggregates;
using EntityFrameworkCore.CreatedUpdatedDate.Extensions;
using Microsoft.EntityFrameworkCore;

namespace ejemplo_eb.Shared.Infrastructure.Persistence.EFC.Configuration;

/// <summary>
///     Application database context for the Learning Center Platform
/// </summary>
/// <param name="options">
///     The options for the database context
/// </param>
/// 


public class AppDbContext(DbContextOptions options) : DbContext(options)
{

    public DbSet<Product> Products { get; set; }  // <--- Aquí agregas tu DbSet

    public DbSet<BillOfMaterialsItem> BillOfMaterialsItems { get; set; } // Agrega el DbSet para BillOfMaterialsItem

    /// <summary>
    ///     On configuring the database context
    /// </summary>
    /// <remarks>
    ///     This method is used to configure the database context.
    ///     It also adds the created and updated date interceptor to the database context.
    /// </remarks>
    /// <param name="builder">
    ///     The option builder for the database context
    /// </param>
    protected override void OnConfiguring(DbContextOptionsBuilder builder)
    {
        builder.AddCreatedUpdatedInterceptor();
        base.OnConfiguring(builder);
    }



    /// <summary>
    ///     On creating the database model
    /// </summary>
    /// <remarks>
    ///     This method is used to create the database model for the application.
    /// </remarks>
    /// <param name="builder">
    ///     The model builder for the database context
    /// </param>
    /// 
    protected override void OnModelCreating(ModelBuilder builder)
    {
        base.OnModelCreating(builder);

        builder.UseSnakeCaseNamingConvention();

        // Opcional: configuraciones adicionales para Product
        builder.Entity<Product>(entity =>
        {
            entity.HasIndex(p => p.ProductNumber).IsUnique();
            entity.HasIndex(p => p.Name).IsUnique();

            // Si quieres mapear enum a int explícitamente (por si acaso)
            entity.Property(p => p.ProductType).HasConversion<int>();
        });

        // Configuración completa para BillOfMaterialsItem
        builder.Entity<BillOfMaterialsItem>(entity =>
        {
            entity.HasKey(b => b.Id);

            entity.Property(b => b.Id)
                  .HasColumnName("id")
                  .IsRequired();

            // Campos simples mapeados directamente
            entity.Property(b => b.BillOfMaterialsId)
                  .HasColumnName("BillOfMaterialsId")
                  .IsRequired();

            entity.Property(b => b.ItemProductNumber)
                  .HasColumnName("ItemProductNumber")
                  .IsRequired();

            entity.Property(b => b.BatchId)
                  .HasColumnName("BatchId")
                  .IsRequired();

            entity.Property(b => b.RequiredQuantity)
                  .HasColumnName("RequiredQuantity")
                  .IsRequired();

            entity.Property(b => b.ScheduledStartAt)
                  .HasColumnName("ScheduledStartAt")
                  .IsRequired();

            entity.Property(b => b.RequiredAt)
                  .HasColumnName("RequiredAt")
                  .IsRequired();

            // Value Objects (si tienes otros aparte de los campos simples, los mapeas aquí)
            // Por ejemplo, si tienes ProductName, ProductCode, Quantity como VO, los mapeas así:
            /*
            entity.OwnsOne(b => b.ProductName, pn =>
            {
                pn.Property(p => p.Value)
                  .HasColumnName("product_name")
                  .IsRequired();
            });

            entity.OwnsOne(b => b.ProductCode, pc =>
            {
                pc.Property(p => p.Value)
                  .HasColumnName("product_code")
                  .IsRequired();
            });

            entity.OwnsOne(b => b.Quantity, q =>
            {
                q.Property(p => p.Value)
                 .HasColumnName("quantity")
                 .IsRequired();
            });
            */

            entity.Property(b => b.CreatedAt)
                  .HasColumnName("created_at")
                  .IsRequired();

            entity.Property(b => b.UpdatedAt)
                  .HasColumnName("updated_at")
                  .IsRequired();

            // Puedes agregar índices únicos o restricciones para validar la clave compuesta, ej:
            entity.HasIndex(b => new { b.ItemProductNumber, b.BatchId, b.BillOfMaterialsId })
                  .IsUnique()
                  .HasDatabaseName("uq_bomitem_composite_key");
        });

    }
}

