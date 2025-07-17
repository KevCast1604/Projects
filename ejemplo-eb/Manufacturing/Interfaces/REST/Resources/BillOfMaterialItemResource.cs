namespace ejemplo_eb.Manufacturing.Interfaces.REST.Resources
{
    public record BillOfMaterialsItemResource(
        int Id,
        int BillOfMaterialsId,
        string ItemProductNumber, // Guid en string para JSON
        int BatchId,
        int RequiredQuantity,
        DateTime ScheduledStartAt,
        DateTime RequiredAt,
        DateTime CreatedAt,
        DateTime UpdatedAt
    );
}
