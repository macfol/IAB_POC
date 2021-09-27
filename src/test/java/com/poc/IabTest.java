package com.poc;

import com.iabtcf.decoder.TCString;
import com.iabtcf.encoder.PublisherRestrictionEntry;
import com.iabtcf.encoder.TCStringEncoder;
import com.iabtcf.utils.BitSetIntIterable;
import com.iabtcf.v2.RestrictionType;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class IabTest {

    @Test
    void testThatFails1() {
        final String encoded = TCStringEncoder.newBuilder()
                .version(2)
                .created(Instant.now())
                .lastUpdated(Instant.now())
                .tcfPolicyVersion(2)
                .cmpId(1)
                .isServiceSpecific(true)
                .useNonStandardStacks(false)
                .purposeOneTreatment(false)
                .publisherCC("DE")
                .cmpVersion(1)
                .consentLanguage("DE")
                .vendorListVersion(108)
                .addVendorConsent(BitSetIntIterable.from(700))
                .addVendorLegitimateInterest(BitSetIntIterable.from(700))
                .addPurposesConsent(BitSetIntIterable.from(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
                .addPurposesLITransparency(BitSetIntIterable.from(2, 3, 4, 5, 6, 7, 8, 9, 10))
                .addPublisherRestrictionEntry(createRestrictions())
                .encode();

        final TCString decoded = TCString.decode(encoded);
        assertThat(decoded).isNotNull();
    }

    static final List<Integer> vendorIds = List.of(1,2,4,6,7,8,9,10,11,12,13,14,15,16,18,20,21,22,23,24,25,26,27,29,30,31,32,33,34,36,37,39,40,42,44,45,46,47,48,49,50,53,57,58,59,60,61,62,63,65,66,67,68,70,71,72,73,76,77,78,79,80,82,84,85,87,88,90,91,94,95,97,98,100,101,102,104,108,109,110,111,114,115,119,120,122,124,127,128,129,130,132,133,137,138,139,140,141,142,143,147,149,150,151,152,153,154,155,157,158,159,160,161,162,163,164,165,168,173,174,178,179,183,184,185,190,192,193,194,195,196,198,199,202,205,206,208,209,210,211,213,215,216,218,224,226,227,228,231,235,237,238,239,241,242,243,244,246,249,253,254,255,259,262,263,264,266,272,273,274,275,276,277,281,284,290,293,294,295,297,298,301,302,304,308,310,311,312,314,315,316,317,318,321,323,325,328,329,333,336,337,345,347,349,350,351,354,358,359,368,371,373,374,375,377,380,381,382,385,387,388,394,397,402,410,412,413,416,418,422,423,424,427,434,435,436,438,440,447,448,450,452,455,458,461,462,471,473,475,479,482,490,491,493,495,496,497,498,501,502,506,507,508,509,511,512,516,517,519,521,524,527,528,530,531,535,536,543,544,545,549,553,556,561,565,568,569,570,571,573,574,577,579,584,587,591,593,596,597,601,602,606,607,610,614,615,617,618,621,624,625,626,628,631,638,639,644,645,646,647,649,650,652,655,656,658,662,663,664,665,666,667,668,670,671,672,674,676,678,681,682,683,685,686,687,690,691,694,697,699,702,703,707,708,709,711,712,713,715,716,717,718,719,720,721,723,725,726,727,728,731,732,733,734,735,736,737,738,739,741,742,744,745,746,747,748,749,750,753,755,756,758,759,760,761,765,766,767,768,769,771,773,775,776,778,779,780,781,782,785,787,788,790,791,793,794,795,796,797,798,799,800,801,804,806,808,809,811,814,816,820,821,822,824,826,827,829,831,832,833,835,837,839,840,844,846,847,848,849,852,854,855,856,857,858,859,860,861,862,863,864,865,866,867,868,869,871,874,875,876,877,879,880,882,884,885,886,888,889,890,891,893,894,895,897,900,901,902,903,905,906,908,909,910,912,915,917,918,920,921,922,924,926,928,929,930,932,933,934,935,936,937,938,941,942,943,944,946,948,951,952,955,957,958,959,961,962,963,964,966,967,968,971,972,973,974,975,976,977,978,979,980,981,982,983,984,985,986,987,988,990,991,992,993,994,995,996,998,999,1000,1001,1002,1004,1006,1007,1010,1011,1012,1015,1016,1018,1019,1020,1021,1022,1023,1024,1025,1026,1027,1028,1029,1030,1031,1032,1033,1034,1035,1036,1037,1038,1039,1040,1042,1045,1046,1047,1048,1049,1050);

    @Test
    void testThatFails2() {
        final Set<Integer> purposeConsent = Set.of(2,3,4,5,6,7,8,9,10);
        final Collection<PublisherRestrictionEntry> restrictionEntries = createRestrictions(vendorIds, purposeConsent);
        final String encoded = TCStringEncoder.newBuilder()
                .version(2)
                .created(Instant.now())
                .lastUpdated(Instant.now())
                .tcfPolicyVersion(2)
                .cmpId(1)
                .isServiceSpecific(true)
                .useNonStandardStacks(false)
                .purposeOneTreatment(false)
                .publisherCC("DE")
                .cmpVersion(1)
                .consentLanguage("DE")
                .vendorListVersion(108)
                .addCustomPurposesConsent(BitSetIntIterable.from(25,26,29))
                .addVendorConsent(BitSetIntIterable.from(vendorIds))
                .addVendorLegitimateInterest(BitSetIntIterable.from(10,32,52,69,76,91,755))
                .addPurposesConsent(BitSetIntIterable.from(purposeConsent))
                .addPurposesLITransparency(BitSetIntIterable.from(2, 3, 4, 5, 6, 7, 8, 9, 10))
                .addPublisherRestrictionEntry(restrictionEntries)
                .encode();

        final TCString decoded = TCString.decode(encoded);
        assertThat(decoded).isNotNull();
    }

    private Collection<PublisherRestrictionEntry> createRestrictions() {
        return Stream.of(
                PublisherRestrictionEntry.newBuilder()
                        .purposeId(2)
                        .restrictionType(RestrictionType.REQUIRE_CONSENT)
                        .addVendor(BitSetIntIterable.from(11, 21, 25, 28, 34, 50, 60, 68, 79, 85, 195, 253, 580, 804, 807, 814))
                        .build(),

                PublisherRestrictionEntry.newBuilder()
                        .purposeId(3)
                        .restrictionType(RestrictionType.REQUIRE_CONSENT)
                        .addVendor(BitSetIntIterable.from(76, 92, 469, 580, 807))
                        .build(),

                PublisherRestrictionEntry.newBuilder()
                        .purposeId(4)
                        .restrictionType(RestrictionType.REQUIRE_CONSENT)
                        .addVendor(BitSetIntIterable.from(76, 469, 580, 807))
                        .build(),

                PublisherRestrictionEntry.newBuilder()
                        .purposeId(5)
                        .restrictionType(RestrictionType.REQUIRE_CONSENT)
                        .addVendor(BitSetIntIterable.from(76, 92, 667, 755))
                        .build(),

                PublisherRestrictionEntry.newBuilder()
                        .purposeId(6)
                        .restrictionType(RestrictionType.REQUIRE_CONSENT)
                        .addVendor(BitSetIntIterable.from(76, 755))
                        .build(),

                PublisherRestrictionEntry.newBuilder()
                        .purposeId(7)
                        .restrictionType(RestrictionType.REQUIRE_CONSENT)
                        .addVendor(BitSetIntIterable.from(11, 21, 25, 28, 34, 50, 60, 62, 68, 79, 85, 92, 95, 98, 152, 195, 253, 278, 580, 804, 807, 814))
                        .build(),

                PublisherRestrictionEntry.newBuilder()
                        .purposeId(8)
                        .restrictionType(RestrictionType.REQUIRE_CONSENT)
                        .addVendor(BitSetIntIterable.from(11, 25, 34, 76, 85, 92, 95, 98, 580, 807))
                        .build(),

                PublisherRestrictionEntry.newBuilder()
                        .purposeId(9)
                        .restrictionType(RestrictionType.REQUIRE_CONSENT)
                        .addVendor(BitSetIntIterable.from(11, 25, 28, 34, 60, 68, 85, 92, 95, 98, 580, 807))
                        .build(),

                PublisherRestrictionEntry.newBuilder()
                        .purposeId(10)
                        .restrictionType(RestrictionType.REQUIRE_CONSENT)
                        .addVendor(BitSetIntIterable.from(11, 21, 25, 28, 34, 50, 60, 68, 79, 82, 85, 95, 98, 253, 278, 580, 804, 807, 814))
                        .build()
        ).collect(Collectors.toUnmodifiableList());
    }

    private Collection<PublisherRestrictionEntry> createRestrictions(final Collection<Integer> vendorIds, final Collection<Integer> purposeIds) {
        return   createRestrictions().stream()
                .filter(restrictions -> purposeIds.contains(restrictions.getPurposeId()))
                .map(restrictions -> {

                    final Set<Integer> consentedVendors = restrictions.getVendors().toSet().stream()
                            .filter(vendorIds::contains)
                            .collect(Collectors.toSet());

                    return PublisherRestrictionEntry.newBuilder()
                            .purposeId(restrictions.getPurposeId())
                            //currently we only plan to use REQUIRE_CONSENT but in the future we might have to extend
                            .restrictionType(restrictions.getRestrictionType())
                            .addVendor(BitSetIntIterable.from(consentedVendors))
                            .build();
                }).collect(Collectors.toSet());
    }
}
