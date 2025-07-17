using ejemplo_eb.Shared.Domain.Aggregates;
using Microsoft.AspNetCore.Http.HttpResults;

namespace ejemplo_eb.Manufacturing.Domain.Model.Aggregates;

public class BillOfMaterialsItem : AuditableModel
{
    public int Id { get; private set; }
    public int BillOfMaterialsId { get; private set; }
    public Guid ItemProductNumber { get; private set; }
    public int BatchId { get; private set; }
    public int RequiredQuantity { get; private set; }
    public DateTime ScheduledStartAt { get; private set; }
    public DateTime RequiredAt { get; private set; }

    // Constructor privado para EF Core
    private BillOfMaterialsItem() { }

    public BillOfMaterialsItem(
        int billOfMaterialsId,
        Guid itemProductNumber,
        int batchId,
        int requiredQuantity,
        DateTime scheduledStartAt,
        DateTime requiredAt)
    {
        BillOfMaterialsId = billOfMaterialsId;
        ItemProductNumber = itemProductNumber;
        BatchId = batchId;
        RequiredQuantity = requiredQuantity;
        ScheduledStartAt = scheduledStartAt;
        RequiredAt = requiredAt;

        CreatedAt = DateTime.UtcNow;
        UpdatedAt = DateTime.UtcNow;
    }

    // Método para actualizar quantity, si lo necesitas
    public void UpdateRequiredQuantity(int newQuantity)
    {
        RequiredQuantity = newQuantity;
        UpdatedAt = DateTime.UtcNow;
    }
}
