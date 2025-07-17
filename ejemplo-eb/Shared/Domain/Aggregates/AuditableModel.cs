using System;

namespace ejemplo_eb.Shared.Domain.Aggregates
{
    public abstract class AuditableModel
    {
        public DateTime CreatedAt { get; protected set; }
        public DateTime UpdatedAt { get; protected set; }
    }
}
