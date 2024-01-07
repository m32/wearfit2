import datetime
import decimal
import sqlalchemy as sa
import sqlalchemy.orm as saorm
import sqlalchemy.sql as sasql

class Base(saorm.DeclarativeBase):
    pass

class Measure(saorm.MappedAsDataclass, Base):
    __tablename__ = "measure"
    #
    synced: saorm.Mapped[datetime.datetime] = saorm.mapped_column()
    line: saorm.Mapped[str] = saorm.mapped_column(sa.String())
    id: saorm.Mapped[int] = saorm.mapped_column(primary_key=True, default=None)
    #
    when: saorm.Mapped[datetime.datetime] = saorm.mapped_column(nullable=True, default=None)
    #
    batteryPercent: saorm.Mapped[int] = saorm.mapped_column(nullable=True, default=None)
    StepCount: saorm.Mapped[int] = saorm.mapped_column(nullable=True, default=None)
    Calory: saorm.Mapped[int] = saorm.mapped_column(nullable=True, default=None)
    Shallow_sleep_time: saorm.Mapped[int] = saorm.mapped_column(nullable=True, default=None)
    Deep_sleep_time: saorm.Mapped[int] = saorm.mapped_column(nullable=True, default=None)
    Sleep_time: saorm.Mapped[int] = saorm.mapped_column(nullable=True, default=None)
    Wakeup_times: saorm.Mapped[int] = saorm.mapped_column(nullable=True, default=None)
    BodyTemp: saorm.Mapped[decimal.Decimal] = saorm.mapped_column(sa.DECIMAL(4, 2), nullable=True, default=None)
    Mianyi: saorm.Mapped[int] = saorm.mapped_column(nullable=True, default=None)
    HeartRate: saorm.Mapped[int] = saorm.mapped_column(nullable=True, default=None)
    BloodOxygen: saorm.Mapped[int] = saorm.mapped_column(nullable=True, default=None)
    BloodPressure_high: saorm.Mapped[int] = saorm.mapped_column(nullable=True, default=None)
    BloodPressure_low: saorm.Mapped[int] = saorm.mapped_column(nullable=True, default=None)
    Sleep_id: saorm.Mapped[int] = saorm.mapped_column(nullable=True, default=None)
    Sleep_time: saorm.Mapped[int] = saorm.mapped_column(nullable=True, default=None)
    Type: saorm.Mapped[int] = saorm.mapped_column(nullable=True, default=None)

    sa.Index("k_id", id)
    sa.Index("k_synced", synced)
    sa.Index("k_when", when)
